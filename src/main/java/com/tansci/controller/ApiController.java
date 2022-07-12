package com.tansci.controller;

import com.tansci.common.WrapMapper;
import com.tansci.common.Wrapper;
import com.tansci.domain.Goods;
import com.tansci.domain.dto.ApiDto;
import com.tansci.service.ApiService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @path：com.tansci.controller.ApiController.java
 * @className：ApiController.java
 * @description：API接口
 * @author：tanyp
 * @dateTime：2022/3/29 20:37
 * @editNote：
 */
@Slf4j
@RestController
@RequestMapping("/api")
@Api(value = "api", tags = "API接口")
public class ApiController {

    @Autowired
    private ApiService apiService;

    /**
     * @methodName：goodsList
     * @description：商品列表
     * @author：tanyp
     * @dateTime：2022/3/29 21:15
     * @Params： []
     * @Return： com.tansci.common.Wrapper<java.util.List < com.tansci.domain.Goods>>
     * @editNote：
     */
    @PostMapping("/goodsList/{userId}")
    public Wrapper<List<Goods>> goodsList(@PathVariable("userId") String userId) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, apiService.goodsList(userId));
    }

    /**
     * @methodName：payment
     * @description：支付创建订单
     * @author：tanyp
     * @dateTime：2022/3/29 21:15
     * @Params： [dto]
     * @Return： com.tansci.common.Wrapper<java.lang.Object>
     * @editNote：
     */
    @PostMapping("/payment")
    public Wrapper<Object> payment(@RequestBody ApiDto dto) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, apiService.payment(dto));
    }

}
