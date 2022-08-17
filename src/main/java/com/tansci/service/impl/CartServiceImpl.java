package com.tansci.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tansci.domain.Cart;
import com.tansci.domain.Goods;
import com.tansci.domain.Shop;
import com.tansci.domain.SysUser;
import com.tansci.exception.BusinessException;
import com.tansci.mapper.CartMapper;
import com.tansci.service.CartService;
import com.tansci.service.GoodsService;
import com.tansci.service.ShopService;
import com.tansci.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @path：com.tansci.service.impl.CartServiceImpl.java
 * @className：CartServiceImpl.java
 * @description： 购物车
 * @author：tanyp
 * @dateTime：2022/7/21 13:00
 * @editNote：
 */
@Slf4j
@Service
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements CartService {

    @Autowired
    private ShopService shopService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private SysUserService sysUserService;

    @Override
    public IPage<Cart> page(Page page, Cart cart) {
        IPage<Cart> iPage = this.baseMapper.selectPage(page,
                Wrappers.<Cart>lambdaQuery()
                        .eq(Objects.nonNull(cart.getStatus()), Cart::getStatus, cart.getStatus())
                        .eq(Objects.nonNull(cart.getGoodsId()), Cart::getGoodsId, cart.getGoodsId())
                        .eq(Objects.nonNull(cart.getShopId()), Cart::getShopId, cart.getShopId())
                        .orderByDesc(Cart::getUpdateTime)
        );

        if (Objects.nonNull(iPage.getRecords()) && iPage.getRecords().size() > 0) {
            List<Shop> shops = shopService.listByIds(iPage.getRecords().stream().map(Cart::getShopId).collect(Collectors.toList()));
            List<Goods> goods = goodsService.listByIds(iPage.getRecords().stream().map(Cart::getGoodsId).collect(Collectors.toList()));
            iPage.getRecords().forEach(item -> {
                Optional<Shop> sOptional = shops.stream().filter(s -> Objects.equals(s.getShopId(), item.getShopId())).findFirst();
                if (sOptional.isPresent()) {
                    item.setShopName(sOptional.get().getName());
                }

                Optional<Goods> gOptional = goods.stream().filter(s -> Objects.equals(s.getGoodsId(), item.getGoodsId())).findFirst();
                if (gOptional.isPresent()) {
                    item.setGoodsName(gOptional.get().getName());
                }
            });
        }
        return iPage;
    }

    @Override
    public List<Cart> list(Cart cart) {
        // 获取用户信息
        SysUser user = sysUserService.getOne(Wrappers.<SysUser>lambdaQuery().eq(SysUser::getUsername, cart.getUsername()));
        if (Objects.isNull(user)) {
            throw new BusinessException("登录已失效，请重新登录！");
        }

        List<Cart> carts = this.baseMapper.selectList(
                Wrappers.<Cart>lambdaQuery()
                        .eq(Cart::getStatus, 0)
                        .eq(Cart::getUserId, user.getId())
        );

        if (Objects.nonNull(carts) && carts.size() > 0) {
            List<Shop> shops = shopService.listByIds(carts.stream().map(Cart::getShopId).collect(Collectors.toList()));
            List<Goods> goods = goodsService.listByIds(carts.stream().map(Cart::getGoodsId).collect(Collectors.toList()));
            carts.forEach(item -> {
                Optional<Shop> sOptional = shops.stream().filter(s -> Objects.equals(s.getShopId(), item.getShopId())).findFirst();
                if (sOptional.isPresent()) {
                    item.setShopName(sOptional.get().getName());
                }

                Optional<Goods> gOptional = goods.stream().filter(s -> Objects.equals(s.getGoodsId(), item.getGoodsId())).findFirst();
                if (gOptional.isPresent()) {
                    Goods goodsInfo = gOptional.get();
                    goodsInfo.setCartId(item.getCartId());
                    goodsInfo.setGoodsNum(item.getGoodsNum());
                    goodsInfo.setIsSelect(1);
                    item.setGoods(goodsInfo);
                }
            });
        }

        // 按店铺分组
        List<Cart> cartList = new ArrayList<>();
        Map<String, List<Cart>> map = carts.stream().collect(Collectors.groupingBy(Cart::getShopId));
        map.forEach((key, val) -> {
            cartList.add(
                    Cart.builder()
                            .shopId(key)
                            .shopName(val.get(0).getShopName())
                            .goodsList(val.stream().map(Cart::getGoods).collect(Collectors.toList()))
                            .isSelect(1)
                            .build()
            );
        });
        return cartList;
    }

    @Override
    public Object add(Cart cart) {
        // 根据商品id获取商户信息
        Goods goods = goodsService.getById(cart.getGoodsId());
        if (Objects.isNull(goods)) {
            throw new BusinessException("该商品已下架！");
        }

        cart.setUpdateTime(LocalDateTime.now());
        cart.setCreateTime(LocalDateTime.now());
        return this.baseMapper.insert(cart);
    }

}
