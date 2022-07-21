package com.tansci.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tansci.domain.Cart;
import com.tansci.mapper.CartMapper;
import com.tansci.service.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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

    @Override
    public IPage<Cart> page(Page page, Cart cart) {
        IPage<Cart> iPage = this.baseMapper.selectPage(page,
                Wrappers.<Cart>lambdaQuery()
                        .orderByDesc(Cart::getUpdateTime)
        );
        return iPage;
    }

    @Override
    public Object delete(Cart cart) {
        return this.baseMapper.deleteById(cart.getCartId());
    }

}
