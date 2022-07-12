package com.tansci.controller;

import com.tansci.common.WrapMapper;
import com.tansci.common.Wrapper;
import com.tansci.domain.SysMenu;
import com.tansci.domain.dto.SysMenuDto;
import com.tansci.service.SysMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @ClassName： SysMenuController.java
 * @ClassPath： com.tansci.controller.SysMenuController.java
 * @Description： 菜单
 * @Author： tanyp
 * @Date： 2021/7/20 17:08
 **/
@Slf4j
@RestController
@RequestMapping("/menu")
@Api(value = "menu", tags = "菜单")
public class SysMenuController {

    @Autowired
    private SysMenuService sysMenuService;

    @ApiOperation(value = "菜单列表", notes = "菜单列表")
    @GetMapping("/list")
    public Wrapper<List<SysMenu>> list(SysMenuDto dto) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, sysMenuService.list(dto));
    }

    @ApiOperation(value = "添加菜单", notes = "添加菜单")
    @PostMapping("/save")
    public Wrapper<Boolean> save(@RequestBody SysMenu sysMenu) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, sysMenuService.save(sysMenu));
    }

    @ApiOperation(value = "修改菜单", notes = "修改菜单")
    @PostMapping("/update")
    public Wrapper<Boolean> update(@RequestBody SysMenu sysMenu) {
        sysMenu.setUpdateTime(LocalDateTime.now());
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, sysMenuService.updateById(sysMenu));
    }

    @ApiOperation(value = "删除菜单", notes = "删除菜单")
    @GetMapping("/del")
    public Wrapper<Boolean> del(Integer id) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, sysMenuService.del(id));
    }

    @ApiOperation(value = "用户菜单权限", notes = "用户菜单权限")
    @PostMapping("/userMenuSave")
    public Wrapper<Boolean> userMenuSave(@RequestBody SysMenuDto dto) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, sysMenuService.userMenuSave(dto));
    }

}
