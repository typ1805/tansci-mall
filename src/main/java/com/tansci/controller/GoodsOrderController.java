package com.tansci.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tansci.common.WrapMapper;
import com.tansci.common.Wrapper;
import com.tansci.domain.GoodsOrder;
import com.tansci.domain.dto.GoodsOrderDto;
import com.tansci.service.GoodsOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * @ClassName： GoodsOrderController.java
 * @ClassPath： com.tansci.controller.GoodsOrderController.java
 * @Description： 商品订单
 * @Author： tanyp
 * @Date： 2022/3/29 13:04
 **/
@Slf4j
@RestController
@RequestMapping("/goodsOrder")
@Api(value = "goodsOrder", tags = "商品订单")
public class GoodsOrderController {

    @Autowired
    private GoodsOrderService goodsOrderService;

    @ApiOperation(value = "分页", notes = "分页")
    @GetMapping("/page")
    public Wrapper<IPage<GoodsOrder>> page(Page page, GoodsOrderDto dto) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, goodsOrderService.page(page, dto));
    }

    @ApiOperation(value = "添加", notes = "添加")
    @PostMapping("/update")
    public Wrapper<Boolean> update(@RequestBody GoodsOrder order) {
        order.setUpdateTime(LocalDateTime.now());
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, goodsOrderService.updateById(order));
    }

    @ApiOperation(value = "删除", notes = "删除")
    @GetMapping("/del")
    public Wrapper<Boolean> del(String id) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, goodsOrderService.removeById(id));
    }

}
