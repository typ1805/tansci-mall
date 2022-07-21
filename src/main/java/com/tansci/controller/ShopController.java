package com.tansci.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tansci.common.WrapMapper;
import com.tansci.common.Wrapper;
import com.tansci.domain.Shop;
import com.tansci.service.ShopService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * @path：com.tansci.controller.ShopController.java
 * @className：ShopController.java
 * @description： 店铺
 * @author：tanyp
 * @dateTime：2022/7/21 13:24 
 * @editNote：
 */
@Slf4j
@RestController
@RequestMapping("/shop")
@Api(value = "shop", tags = "店铺")
public class ShopController {

    @Autowired
    private ShopService shopService;

    @ApiOperation(value = "分页", notes = "分页")
    @GetMapping("/page")
    public Wrapper<IPage<Shop>> page(Page page, Shop shop) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, shopService.page(page, shop));
    }

    @ApiOperation(value = "添加", notes = "添加")
    @PostMapping("/save")
    public Wrapper<Object> save(@RequestBody Shop shop) {
        shop.setUpdateTime(LocalDateTime.now());
        shop.setCreateTime(LocalDateTime.now());
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, shopService.save(shop));
    }

    @ApiOperation(value = "更新", notes = "更新")
    @PostMapping("/update")
    public Wrapper<Object> update(@RequestBody Shop shop) {
        shop.setUpdateTime(LocalDateTime.now());
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, shopService.updateById(shop));
    }

    @ApiOperation(value = "删除", notes = "删除")
    @GetMapping("/delete")
    public Wrapper<Object> delete(Shop shop) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, shopService.delete(shop));
    }

}
