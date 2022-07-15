package com.tansci.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tansci.common.WrapMapper;
import com.tansci.common.Wrapper;
import com.tansci.domain.SysMenuRole;
import com.tansci.domain.SysRole;
import com.tansci.domain.SysUserRole;
import com.tansci.domain.dto.SysRoleDto;
import com.tansci.service.SysMenuRoleService;
import com.tansci.service.SysRoleService;
import com.tansci.service.SysUserRoleService;
import com.tansci.utils.SecurityUserUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @ClassName： SysRoleController.java
 * @ClassPath： com.tansci.controller.SysRoleController.java
 * @Description： 角色管理
 * @Author： tanyp
 * @Date： 2022/4/25 16:36
 **/
@Slf4j
@RestController
@RequestMapping("/role")
@Api(value = "role", tags = "角色管理")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysUserRoleService sysUserRoleService;
    @Autowired
    private SysMenuRoleService sysMenuRoleService;

    @ApiOperation(value = "角色分页", notes = "角色分页")
    @GetMapping("/page")
    public Wrapper<IPage<SysRole>> page(Page page, SysRoleDto dto) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, sysRoleService.page(page, dto));
    }

    @ApiOperation(value = "列表", notes = "列表")
    @GetMapping("/list")
    public Wrapper<List<SysRole>> list(SysRole sysRole) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, sysRoleService.list(sysRole));
    }

    @ApiOperation(value = "添加", notes = "添加")
    @PostMapping("save")
    public Wrapper<Object> save(@RequestBody SysRole sysRole) {
        sysRole.setCreateTime(LocalDateTime.now());
        sysRole.setUpdateTime(LocalDateTime.now());
        sysRole.setCreator(SecurityUserUtils.getUser().getId());
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, sysRoleService.save(sysRole));
    }

    @ApiOperation(value = "删除", notes = "删除")
    @GetMapping("delete")
    public Wrapper<Object> delete(SysRoleDto dto) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, sysRoleService.delete(dto));
    }

    @ApiOperation(value = "修改", notes = "修改")
    @PostMapping("update")
    public Wrapper<Object> update(@RequestBody SysRole sysRole) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, sysRoleService.updateById(sysRole));
    }

    @ApiOperation(value = "添加用户角色", notes = "添加用户角色")
    @PostMapping("userRoleSave")
    public Wrapper<Object> userRoleSave(@RequestBody SysUserRole sysUserRole) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, sysUserRoleService.userRoleSave(sysUserRole));
    }

    @ApiOperation(value = "添加菜单角色", notes = "添加菜单角色")
    @PostMapping("menuRoleSave")
    public Wrapper<Object> menuRoleSave(@RequestBody SysMenuRole sysMenuRole) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, sysMenuRoleService.menuRoleSave(sysMenuRole));
    }

}
