package com.tansci.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tansci.domain.Goods;
import com.tansci.domain.Shop;
import com.tansci.domain.SysUser;
import com.tansci.mapper.ShopMapper;
import com.tansci.service.GoodsService;
import com.tansci.service.ShopService;
import com.tansci.service.SysUserService;
import com.tansci.utils.SecurityUserUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @path：com.tansci.service.impl.ShopServiceImpl.java
 * @className：ShopServiceImpl.java
 * @description： 店铺
 * @author：tanyp
 * @dateTime：2022/7/21 13:23
 * @editNote：
 */
@Slf4j
@Service
public class ShopServiceImpl extends ServiceImpl<ShopMapper, Shop> implements ShopService {

    @Autowired
    private GoodsService goodsService;
    @Autowired
    private SysUserService sysUserService;

    @Override
    public IPage<Shop> page(Page page, Shop shop) {
        if (Objects.isNull(shop.getUserId()) && !Objects.equals(1, SecurityUserUtils.getUser().getType())) {
            shop.setUserId(SecurityUserUtils.getUser().getId());
        }

        IPage<Shop> iPage = this.baseMapper.selectPage(page,
                Wrappers.<Shop>lambdaQuery()
                        .eq(Objects.nonNull(shop.getShopId()), Shop::getShopId, shop.getShopId())
                        .eq(Objects.nonNull(shop.getStatus()), Shop::getStatus, shop.getStatus())
                        .eq(Objects.nonNull(shop.getUserId()), Shop::getUserId, shop.getUserId())
                        .like(Objects.nonNull(shop.getName()), Shop::getName, shop.getName())
                        .orderByDesc(Shop::getUpdateTime)
        );

        if (Objects.nonNull(iPage.getRecords()) && iPage.getRecords().size() > 0) {
            List<SysUser> userList = sysUserService.listByIds(iPage.getRecords().stream().map(Shop::getUserId).distinct().collect(Collectors.toList()));
            iPage.getRecords().forEach(item -> {
                Optional<SysUser> uOptional = userList.stream().filter(u -> Objects.equals(u.getId(), item.getUserId())).findFirst();
                if (uOptional.isPresent()) {
                    item.setUsername(uOptional.get().getUsername());
                }
            });
        }
        return iPage;
    }

    @Override
    public Object delete(Shop shop) {
        int row = this.baseMapper.deleteById(shop.getShopId());
        if (row > 0) {
            return goodsService.remove(Wrappers.<Goods>lambdaQuery().eq(Goods::getShopId, shop.getShopId()));
        }
        return null;
    }
}
