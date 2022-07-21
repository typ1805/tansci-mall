package com.tansci.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tansci.common.Enums;
import com.tansci.domain.SysDic;
import com.tansci.domain.dto.SysDicDto;
import com.tansci.mapper.SysDicMapper;
import com.tansci.service.SysDicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @path：com.tansci.service.impl.SysDicServiceImpl.java
 * @className：SysDicServiceImpl.java
 * @description：字典
 * @author：tanyp
 * @dateTime：2022/2/15 13:51
 * @editNote：
 */
@Slf4j
@Service
public class SysDicServiceImpl extends ServiceImpl<SysDicMapper, SysDic> implements SysDicService {

    @Override
    public List<SysDic> dicList(SysDicDto dto) {
        List<SysDic> list = this.baseMapper.selectList(
                Wrappers.<SysDic>lambdaQuery()
                        .eq(Objects.nonNull(dto.getParentId()), SysDic::getParentId, dto.getParentId())
                        .eq(Objects.nonNull(dto.getGroupName()), SysDic::getGroupName, dto.getGroupName())
                        .eq(Objects.nonNull(dto.getType()), SysDic::getType, dto.getType())
        );

        list.forEach(item -> {
            item.setTypeName(Objects.isNull(item.getType()) ? "" : Enums.getVlaueByGroup(item.getType(), "dic_type"));
        });

        list = list.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(SysDic::getId))), ArrayList::new));
        Map<String, List<SysDic>> map = list.stream().collect(Collectors.groupingBy(SysDic::getParentId, Collectors.toList()));
        list.stream().forEach(item -> item.setChildren(map.get(item.getId())));

        List<SysDic> newList = map.get("0").stream().sorted(Comparator.comparing(SysDic::getSort)).collect(Collectors.toList());
        return newList;
    }

    @Override
    public boolean del(String id) {
        List<String> ids = new ArrayList<>();
        ids.add(id);
        List<SysDic> dicList = this.baseMapper.getDicChildrens(id);
        if (Objects.nonNull(dicList) && dicList.size() > 0) {
            ids.addAll(dicList.stream().map(SysDic::getId).collect(Collectors.toList()));
        }
        this.baseMapper.deleteBatchIds(ids);
        return true;
    }
}