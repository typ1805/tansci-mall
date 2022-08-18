package com.tansci.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tansci.common.WrapMapper;
import com.tansci.common.Wrapper;
import com.tansci.domain.OrderInfo;
import com.tansci.domain.dto.OrderDto;
import com.tansci.service.OrderInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @path：com.tansci.controller.OrderInfoController.java
 * @className：OrderInfoController.java
 * @description： 订单
 * @author：tanyp
 * @dateTime：2022/7/21 13:16
 * @editNote：
 */
@Slf4j
@RestController
@RequestMapping("/order")
@Api(value = "order", tags = "订单")
public class OrderInfoController {

    @Autowired
    private OrderInfoService orderInfoService;

    @ApiOperation(value = "分页", notes = "分页")
    @GetMapping("/page")
    public Wrapper<IPage<OrderInfo>> page(Page page, OrderInfo order) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, orderInfoService.page(page, order));
    }

    @ApiOperation(value = "列表", notes = "列表")
    @GetMapping("/list")
    public Wrapper<List<OrderInfo>> list(OrderInfo order) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, orderInfoService.list(order));
    }

    @ApiOperation(value = "提交订单", notes = "提交订单")
    @PostMapping("/submit")
    public Wrapper<Object> submit(@RequestBody OrderDto dto) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, orderInfoService.submit(dto));
    }

    @ApiOperation(value = "更新", notes = "更新")
    @PostMapping("/update")
    public Wrapper<Object> update(@RequestBody OrderInfo order) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, orderInfoService.update(order));
    }

    @ApiOperation(value = "删除", notes = "删除")
    @GetMapping("/delete")
    public Wrapper<Object> delete(OrderInfo order) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, orderInfoService.delete(order));
    }

}
