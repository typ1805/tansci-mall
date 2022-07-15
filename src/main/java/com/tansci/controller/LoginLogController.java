package com.tansci.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tansci.common.WrapMapper;
import com.tansci.common.Wrapper;
import com.tansci.domain.LoginLog;
import com.tansci.service.LoginLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @path：com.tansci.controller.LoginLogController.java
 * @className：LoginLogController.java
 * @description：登录日志
 * @author：tanyp
 * @dateTime：2022/7/15 22:01 
 * @editNote：
 */
@Slf4j
@RestController
@RequestMapping("/loginLog")
@Api(value = "loginLog", tags = "登录日志")
public class LoginLogController {

    @Autowired
    private LoginLogService loginLogService;

    @ApiOperation(value = "登录日志分页")
    @GetMapping("/page")
    public Wrapper<IPage<LoginLog>> page(Page page, LoginLog loginLog) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, loginLogService.page(page, loginLog));
    }

}
