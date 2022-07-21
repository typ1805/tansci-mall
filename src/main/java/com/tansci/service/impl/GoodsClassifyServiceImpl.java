package com.tansci.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tansci.domain.GoodsClassify;
import com.tansci.mapper.GoodsClassifyMapper;
import com.tansci.service.GoodsClassifyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @path：com.tansci.service.impl.GoodsClassifyServiceImpl.java
 * @className：GoodsClassifyServiceImpl.java
 * @description： 商品分类
 * @author：tanyp
 * @dateTime：2022/7/21 13:05 
 * @editNote：
 */
@Slf4j
@Service
public class GoodsClassifyServiceImpl extends ServiceImpl<GoodsClassifyMapper, GoodsClassify> implements GoodsClassifyService {

    @Override
    public List<GoodsClassify> list(GoodsClassify classify) {
        List<GoodsClassify> list = this.baseMapper.selectList(
                Wrappers.<GoodsClassify>lambdaQuery()
                        .eq(Objects.nonNull(classify.getParentId()), GoodsClassify::getParentId, classify.getParentId())
                        .eq(Objects.nonNull(classify.getName()), GoodsClassify::getName, classify.getName())
        );

        list = list.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(GoodsClassify::getId))), ArrayList::new));
        Map<String, List<GoodsClassify>> map = list.stream().collect(Collectors.groupingBy(GoodsClassify::getParentId, Collectors.toList()));
        list.stream().forEach(item -> item.setChildren(map.get(item.getId())));

        List<GoodsClassify> newList = map.get("0").stream().sorted(Comparator.comparing(GoodsClassify::getSort)).collect(Collectors.toList());
        return newList;
    }

    @Override
    public Object delete(GoodsClassify classify) {
        List<String> ids = new ArrayList<>();
        ids.add(classify.getId());
        List<GoodsClassify> dicList = this.baseMapper.getGoodsClassifyChildrens(classify.getId());
        if (Objects.nonNull(dicList) && dicList.size() > 0) {
            ids.addAll(dicList.stream().map(GoodsClassify::getId).collect(Collectors.toList()));
        }
        return this.baseMapper.deleteBatchIds(ids);
    }

}
