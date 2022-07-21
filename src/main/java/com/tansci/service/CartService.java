package com.tansci.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tansci.domain.Cart;

/**
 * @path：com.tansci.service.CartService.java
 * @className：CartService.java
 * @description： 购物车
 * @author：tanyp
 * @dateTime：2022/7/21 12:59 
 * @editNote：
 */
public interface CartService extends IService<Cart> {

    IPage<Cart> page(Page page, Cart cart);

    Object delete(Cart cart);

}
