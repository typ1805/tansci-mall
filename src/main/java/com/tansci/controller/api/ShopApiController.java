package com.tansci.controller.api;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @path：com.tansci.controller.ShopApiController.java
 * @className：ShopApiController.java
 * @description： 店铺API
 * @author：tanyp
 * @dateTime：2022/7/21 13:24
 * @editNote：
 */
@Slf4j
@RestController
@RequestMapping("/api/shop")
@Api(value = "api/shop", tags = "店铺API")
public class ShopApiController {

    @Autowired
    private ShopService shopService;

    @ApiOperation(value = "分页", notes = "分页")
    @GetMapping("/getShopPage")
    public Wrapper<IPage<Shop>> getShopPage(Page page, Shop shop) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, shopService.page(page, shop));
    }

    @ApiOperation(value = "店铺信息", notes = "店铺信息")
    @GetMapping("/getShopInfo")
    public Wrapper<Shop> getShopPage(Shop shop) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, shopService.getById(shop.getShopId()));
    }

}
