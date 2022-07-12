package com.tansci.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tansci.common.Enums;
import com.tansci.domain.Goods;
import com.tansci.domain.SysUser;
import com.tansci.domain.dto.GoodsDto;
import com.tansci.mapper.GoodsMapper;
import com.tansci.service.GoodsService;
import com.tansci.service.SysUserService;
import com.tansci.service.UploadService;
import com.tansci.utils.ImageUtiles;
import com.tansci.utils.SecurityUserUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @path：com.tansci.service.impl.GoodsServiceImpl.java
 * @className：GoodsServiceImpl.java
 * @description：商品
 * @author：tanyp
 * @dateTime：2022/03/29 10:38
 * @editNote：
 */
@Slf4j
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {

    @Autowired
    private UploadService uploadService;
    @Autowired
    private SysUserService sysUserService;

    @Override
    public IPage<Goods> page(Page page, GoodsDto dto) {
        Page<Goods> goodsPage = this.baseMapper.selectPage(page,
                Wrappers.<Goods>lambdaQuery()
                        .like(Objects.nonNull(dto.getName()), Goods::getName, dto.getName())
                        .eq(!Objects.equals(1, SecurityUserUtils.getUser().getType()), Goods::getUserId, SecurityUserUtils.getUser().getId())
                        .eq(Objects.nonNull(dto.getStatus()), Goods::getStatus, dto.getStatus())
                        .between(Objects.nonNull(dto.getStartTime()) && Objects.nonNull(dto.getEndTime()), Goods::getCreateTime, dto.getStartTime(), dto.getEndTime())
                        .orderByDesc(Goods::getUpdateTime)
        );
        if (Objects.nonNull(goodsPage) && goodsPage.getRecords().size() > 0) {
            List<SysUser> users = sysUserService.list();
            goodsPage.getRecords().forEach(item -> {
                item.setStatusName(Enums.getVlaueByGroup(item.getStatus(), "goods_status"));

                Optional<SysUser> uOptional = users.stream().filter(u -> Objects.equals(u.getId(), item.getUserId())).findFirst();
                if (uOptional.isPresent()) {
                    item.setUserName(uOptional.get().getUsername());
                }
            });
        }
        return goodsPage;
    }

    @Transient
    @Override
    public Boolean del(GoodsDto dto) {
        // 删除图片
        Goods goods = this.getById(dto.getId());
        if (Objects.nonNull(goods) && Objects.nonNull(goods.getImagePath())) {
            String fileName = goods.getImagePath().substring(goods.getImagePath().lastIndexOf("/") + 1);
            uploadService.delFile(fileName);
        }

        // 删除详情中的图片
        if (Objects.nonNull(goods) && Objects.nonNull(goods.getDetails())) {
            List<String> pathList = ImageUtiles.getImageSrc(goods.getDetails());
            if (Objects.nonNull(pathList)) {
                pathList.forEach(path -> {
                    String fileName = path.substring(path.lastIndexOf("/") + 1);
                    uploadService.delFile(fileName);
                });
            }
        }
        return this.removeById(dto.getId());
    }

}
