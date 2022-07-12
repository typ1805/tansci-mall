package com.tansci.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tansci.common.WrapMapper;
import com.tansci.common.Wrapper;
import com.tansci.domain.GoodsOrder;
import com.tansci.domain.dto.GoodsOrderDto;
import com.tansci.service.GoodsOrderService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * @ClassName： GoodsOrderController.java
 * @ClassPath： com.tansci.controller.GoodsOrderController.java
 * @Description： 订单
 * @Author： tanyp
 * @Date： 2022/3/29 13:04
 **/
@Slf4j
@RestController
@RequestMapping("/order")
@Api(value = "order", tags = "订单")
public class GoodsOrderController {

    @Autowired
    private GoodsOrderService orderService;

    @GetMapping("/page")
    public Wrapper<IPage<GoodsOrder>> page(Page page, GoodsOrderDto dto) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, orderService.page(page, dto));
    }

    @PostMapping("/update")
    public Wrapper<Boolean> update(@RequestBody GoodsOrder order) {
        order.setUpdateTime(LocalDateTime.now());
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, orderService.updateById(order));
    }

    @GetMapping("/del")
    public Wrapper<Boolean> del(String id) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, orderService.removeById(id));
    }

}
