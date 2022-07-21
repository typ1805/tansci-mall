package com.tansci.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tansci.common.WrapMapper;
import com.tansci.common.Wrapper;
import com.tansci.domain.Refund;
import com.tansci.service.RefundService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @path：com.tansci.controller.RefundController.java
 * @className：RefundController.java
 * @description： 退款
 * @author：tanyp
 * @dateTime：2022/7/21 13:18 
 * @editNote：
 */
@Slf4j
@RestController
@RequestMapping("/refund")
@Api(value = "refund", tags = "退款")
public class RefundController {

    @Autowired
    private RefundService refundService;

    @ApiOperation(value = "分页", notes = "分页")
    @GetMapping("/page")
    public Wrapper<IPage<Refund>> page(Page page, Refund refund) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, refundService.page(page, refund));
    }

    @ApiOperation(value = "添加", notes = "添加")
    @PostMapping("/save")
    public Wrapper<Object> save(@RequestBody Refund refund) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, refundService.save(refund));
    }

    @ApiOperation(value = "更新", notes = "更新")
    @PostMapping("/update")
    public Wrapper<Object> update(@RequestBody Refund refund) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, refundService.update(refund));
    }

    @ApiOperation(value = "删除", notes = "删除")
    @GetMapping("/delete")
    public Wrapper<Object> delete(Refund refund) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, refundService.delete(refund));
    }

}
