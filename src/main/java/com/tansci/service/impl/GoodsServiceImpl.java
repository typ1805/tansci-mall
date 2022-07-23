package com.tansci.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tansci.common.Enums;
import com.tansci.domain.*;
import com.tansci.mapper.GoodsMapper;
import com.tansci.service.*;
import com.tansci.utils.SecurityUserUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

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
    @Autowired
    private GoodsClassifyService goodsClassifyService;
    @Autowired
    private ShopService shopService;
    @Autowired
    private GoodsImageService goodsImageService;

    @Override
    public IPage<Goods> page(Page page, Goods goods) {
        IPage<Goods> iPage = this.baseMapper.selectPage(page,
                Wrappers.<Goods>lambdaQuery()
                        .eq(!Objects.equals(1, SecurityUserUtils.getUser().getType()), Goods::getUserId, SecurityUserUtils.getUser().getId())
                        .like(Objects.nonNull(goods.getName()), Goods::getName, goods.getName())
                        .eq(Objects.nonNull(goods.getShopId()), Goods::getShopId, goods.getShopId())
                        .eq(Objects.nonNull(goods.getStatus()), Goods::getStatus, goods.getStatus())
                        .eq(Objects.nonNull(goods.getClassify()), Goods::getClassify, goods.getClassify())
                        .orderByDesc(Goods::getUpdateTime)
        );

        if (Objects.nonNull(iPage.getRecords()) && iPage.getRecords().size() > 0) {

            List<GoodsClassify> classifyList = goodsClassifyService.listByIds(iPage.getRecords().stream().map(Goods::getClassify).distinct().collect(Collectors.toList()));
            List<SysUser> userList = sysUserService.listByIds(iPage.getRecords().stream().map(Goods::getUserId).distinct().collect(Collectors.toList()));
            List<Shop> shopList = shopService.listByIds(iPage.getRecords().stream().map(Goods::getShopId).distinct().collect(Collectors.toList()));

            iPage.getRecords().forEach(item -> {
                // 商品状态
                item.setStatusName(Objects.nonNull(item.getStatus()) ? Enums.getVlaueByGroup(item.getStatus(), "goods_status") : null);

                // 分类
                Optional<GoodsClassify> cOptional = classifyList.stream().filter(c -> Objects.equals(c.getId(), item.getClassify())).findFirst();
                if (cOptional.isPresent()) {
                    item.setClassifyName(cOptional.get().getName());
                }

                // 用户
                Optional<SysUser> uOptional = userList.stream().filter(u -> Objects.equals(u.getId(), item.getUserId())).findFirst();
                if (uOptional.isPresent()) {
                    item.setUserName(uOptional.get().getUsername());
                }

                // 店铺
                Optional<Shop> sOptional = shopList.stream().filter(s -> Objects.equals(s.getShopId(), item.getShopId())).findFirst();
                if (sOptional.isPresent()) {
                    item.setShopName(sOptional.get().getName());
                    item.setShop(sOptional.get());
                }
            });
        }

        return iPage;
    }

    @Transactional
    @Override
    public boolean save(Goods goods) {
        goods.setUpdateTime(LocalDateTime.now());
        goods.setCreateTime(LocalDateTime.now());
        goods.setUserId(SecurityUserUtils.getUser().getId());
        goods.setShopId(SecurityUserUtils.getUser().getShopId());
        if (Objects.nonNull(goods.getLabelList()) && goods.getLabelList().size() > 0) {
            goods.setLabels(goods.getLabelList().stream().map(GoodsLabel::getId).collect(Collectors.joining(",")));
        }
        int row = this.baseMapper.insert(goods);
        if (row > 0) {
            goods.getImageList().forEach(item -> {
                item.setGoodsId(goods.getGoodsId());
                item.setUpdateTime(LocalDateTime.now());
                item.setCreateTime(LocalDateTime.now());
            });
            return goodsImageService.saveBatch(goods.getImageList());
        }
        return false;
    }

    @Transactional
    @Override
    public Object update(Goods goods) {
        if (Objects.nonNull(goods.getLabelList()) && goods.getLabelList().size() > 0) {
            goods.setLabels(goods.getLabelList().stream().map(GoodsLabel::getId).collect(Collectors.joining(",")));
        }
        int row = this.baseMapper.updateById(goods);
        if (row > 0) {
            // 删除旧数据
            goodsImageService.remove(Wrappers.<GoodsImage>lambdaQuery().eq(GoodsImage::getGoodsId, goods.getGoodsId()));
            // 新增
            goods.getImageList().forEach(item -> {
                item.setGoodsId(goods.getGoodsId());
                item.setUpdateTime(LocalDateTime.now());
                item.setCreateTime(LocalDateTime.now());
            });
            goodsImageService.saveBatch(goods.getImageList());
        }
        return row;
    }

    @Transactional
    @Override
    public Object delete(Goods goods) {
        Goods _goods = this.baseMapper.selectById(goods.getGoodsId());
        if (Objects.nonNull(_goods)) {
            int row = this.baseMapper.deleteById(goods.getGoodsId());
            if (row > 0) {
                // 删除图片
                uploadService.delFile(_goods.getCoverImg().substring(_goods.getCoverImg().lastIndexOf('/') + 1));
                // 删除轮播图
                List<GoodsImage> images = goodsImageService.list(Wrappers.<GoodsImage>lambdaQuery().eq(GoodsImage::getGoodsId, _goods.getGoodsId()));
                if (Objects.nonNull(images) && images.size() > 0) {
                    images.forEach(item -> {
                        uploadService.delFile(item.getName());
                    });
                }
            }
            return row;
        }
        return "商品不存在！";
    }

}
