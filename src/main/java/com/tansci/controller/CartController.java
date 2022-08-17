package com.tansci.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tansci.common.WrapMapper;
import com.tansci.common.Wrapper;
import com.tansci.domain.Cart;
import com.tansci.service.CartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @path：com.tansci.controller.CartController.java
 * @className：CartController.java
 * @description： 购物车
 * @author：tanyp
 * @dateTime：2022/7/21 13:01
 * @editNote：
 */
@Slf4j
@RestController
@RequestMapping("/cart")
@Api(value = "cart", tags = "购物车")
public class CartController {

    @Autowired
    private CartService cartService;

    @ApiOperation(value = "分页", notes = "分页")
    @GetMapping("/page")
    public Wrapper<IPage<Cart>> page(Page page, Cart cart) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, cartService.page(page, cart));
    }

    @ApiOperation(value = "列表", notes = "列表")
    @GetMapping("/list")
    public Wrapper<List<Cart>> list(Cart cart) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, cartService.list(cart));
    }

    @ApiOperation(value = "添加", notes = "添加")
    @PostMapping("/save")
    public Wrapper<Object> save(@RequestBody Cart cart) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, cartService.add(cart));
    }

    @ApiOperation(value = "删除", notes = "删除")
    @GetMapping("/delete")
    public Wrapper<Object> delete(Cart cart) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, cartService.removeById(cart.getCartId()));
    }

    @ApiOperation(value = "删除", notes = "删除")
    @PostMapping("/batchDelete")
    public Wrapper<Object> batchDelete(@RequestBody Cart cart) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, cartService.removeBatchByIds(cart.getCartIds()));
    }

}
