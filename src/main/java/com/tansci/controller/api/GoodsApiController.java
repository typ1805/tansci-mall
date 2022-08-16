package com.tansci.controller.api;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tansci.common.WrapMapper;
import com.tansci.common.Wrapper;
import com.tansci.domain.Goods;
import com.tansci.service.GoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName： GoodsApiController.java
 * @ClassPath： com.tansci.controller.api.GoodsApiController.java
 * @Description： api商品
 * @Author： tanyp
 * @Date： 2022/8/16 10:31
 **/
@Slf4j
@RestController
@RequestMapping("/api/goods")
@Api(value = "api/goods", tags = "API商品")
public class GoodsApiController {

    @Autowired
    private GoodsService goodsService;

    @ApiOperation(value = "商品分页列表", notes = "商品分页列表")
    @GetMapping("/getGoodsPage")
    public Wrapper<IPage<Goods>> getGoodsPage(Page page, Goods goods) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, goodsService.getGoodsPage(page, goods));
    }

    @ApiOperation(value = "商品详情", notes = "商品详情")
    @GetMapping("/getGoodsInfo")
    public Wrapper<Goods> getGoodsInfo(Goods goods) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, goodsService.getGoodsInfo(goods));
    }

}
