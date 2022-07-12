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

    /**
     * @methodName：page
     * @description：用户列表分页
     * @author：tanyp
     * @dateTime：2022/03/29 22:18
     * @Params： [page, dto]
     * @Return： com.tansci.common.Wrapper<com.baomidou.mybatisplus.core.metadata.IPage < com.tansci.domain.SysUser>>
     * @editNote：
     */
    @GetMapping("/page")
    public Wrapper<IPage<SysUser>> page(Page page, SysUserDto dto) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, sysUserService.page(page, dto));
    }

    @GetMapping("/qryByUserName")
    public Wrapper<SysUser> qryByUserName(SysUserDto dto) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, sysUserService.getOne(Wrappers.<SysUser>lambdaQuery().eq(SysUser::getUsername, dto.getUsername())));
    }

    /**
     * @methodName：save
     * @description：添加用户
     * @author：tanyp
     * @dateTime：2022/03/29 22:18
     * @Params： [user]
     * @Return： com.tansci.common.Wrapper<java.lang.Boolean>
     * @editNote：
     */
    @PostMapping("/save")
    public Wrapper<Boolean> save(@RequestBody SysUser user) {
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        // 密码加密
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, sysUserService.save(user));
    }

    /**
     * @methodName：update
     * @description：修改用户
     * @author：tanyp
     * @dateTime：2022/03/29 22:18
     * @Params： [user]
     * @Return： com.tansci.common.Wrapper<java.lang.Boolean>
     * @editNote：
     */
    @PostMapping("/update")
    public Wrapper<Boolean> update(@RequestBody SysUser user) {
        user.setUpdateTime(LocalDateTime.now());
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, sysUserService.updateById(user));
    }

    /**
     * @methodName：save
     * @description：删除
     * @author：tanyp
     * @dateTime：2022/03/29 22:18
     * @Params： [user]
     * @Return： com.tansci.common.Wrapper<java.lang.Boolean>
     * @editNote：
     */
    @GetMapping("/del")
    public Wrapper<Boolean> del(SysUserDto dto) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, sysUserService.removeById(dto.getId()));
    }

    /**
     * @methodName：modifyPass
     * @description：修改密码
     * @author：tanyp
     * @dateTime：2022/03/29 22:18
     * @Params： [user]
     * @Return： com.tansci.common.Wrapper<java.lang.Boolean>
     * @editNote：
     */
    @PostMapping("/modifyPass")
    public Wrapper<Integer> modifyPass(@RequestBody SysUserDto dto) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, sysUserService.modifyPass(dto));
    }

}
