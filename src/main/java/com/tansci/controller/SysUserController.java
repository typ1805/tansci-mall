package com.tansci.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tansci.common.WrapMapper;
import com.tansci.common.Wrapper;
import com.tansci.domain.SysUser;
import com.tansci.domain.dto.SysUserDto;
import com.tansci.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * @path：com.tansci.controller.SysUserController.java
 * @className：SysUserController.java
 * @description：用户
 * @author：tanyp
 * @dateTime：2022/03/29 22:18
 * @editNote：
 */
@Slf4j
@RestController
@RequestMapping("/user")
@Api(value = "user", tags = "用户")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @ApiOperation(value = "用户分页")
    @GetMapping("/page")
    public Wrapper<IPage<SysUser>> page(Page page, SysUserDto dto) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, sysUserService.page(page, dto));
    }

    @ApiOperation(value = "根据用户名称获取用户信息")
    @GetMapping("/qryByUserName")
    public Wrapper<SysUser> qryByUserName(SysUserDto dto) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, sysUserService.getOne(Wrappers.<SysUser>lambdaQuery().eq(SysUser::getUsername, dto.getUsername())));
    }

    @ApiOperation(value = "添加")
    @PostMapping("/save")
    public Wrapper<Boolean> save(@RequestBody SysUser user) {
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        // 密码加密
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, sysUserService.save(user));
    }

    @ApiOperation(value = "更新")
    @PostMapping("/update")
    public Wrapper<Boolean> update(@RequestBody SysUser user) {
        user.setUpdateTime(LocalDateTime.now());
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, sysUserService.updateById(user));
    }

    @ApiOperation(value = "删除")
    @GetMapping("/del")
    public Wrapper<Boolean> del(SysUserDto dto) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, sysUserService.removeById(dto.getId()));
    }

    @ApiOperation(value = "修改密码")
    @PostMapping("/modifyPass")
    public Wrapper<Integer> modifyPass(@RequestBody SysUserDto dto) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, sysUserService.modifyPass(dto));
    }

    @ApiOperation(value = "登录")
    @PostMapping("/login")
    public Wrapper<Object> login(@RequestBody SysUserDto dto) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, sysUserService.login(dto));
    }

}
