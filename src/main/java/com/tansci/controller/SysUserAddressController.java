package com.tansci.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.tansci.common.WrapMapper;
import com.tansci.common.Wrapper;
import com.tansci.domain.SysUserAddress;
import com.tansci.service.SysUserAddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * @ClassName： SysUserAddressController.java
 * @ClassPath： com.tansci.controller.api.SysUserAddressController.java
 * @Description： 商户地址
 * @Author： tanyp
 * @Date： 2022/8/17 10:56
 **/
@Slf4j
@RestController
@RequestMapping("/userAddress")
@Api(value = "userAddress", tags = "商户地址")
public class SysUserAddressController {

    @Autowired
    private SysUserAddressService sysUserAddressService;

    @ApiOperation(value = "分页")
    @GetMapping("/page")
    public Wrapper<List<SysUserAddress>> page(SysUserAddress address) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, sysUserAddressService.list(
                Wrappers.<SysUserAddress>lambdaQuery()
                        .eq(Objects.nonNull(address.getUserId()), SysUserAddress::getUserId, address.getUserId())
                        .like(Objects.nonNull(address.getName()), SysUserAddress::getName, address.getName())
                        .like(Objects.nonNull(address.getPhone()), SysUserAddress::getPhone, address.getPhone())
                        .orderByDesc(SysUserAddress::getUpdateTime)
        ));
    }

    @ApiOperation(value = "列表")
    @GetMapping("/list")
    public Wrapper<List<SysUserAddress>> list(SysUserAddress address) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, sysUserAddressService.list(
                Wrappers.<SysUserAddress>lambdaQuery()
                        .eq(SysUserAddress::getUserId, address.getUserId())
        ));
    }

    @ApiOperation(value = "添加")
    @PostMapping("/save")
    public Wrapper<Boolean> save(@RequestBody SysUserAddress address) {
        address.setCreateTime(LocalDateTime.now());
        address.setUpdateTime(LocalDateTime.now());
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, sysUserAddressService.save(address));
    }

    @ApiOperation(value = "更新")
    @PostMapping("/update")
    public Wrapper<Boolean> update(@RequestBody SysUserAddress address) {
        address.setUpdateTime(LocalDateTime.now());
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, sysUserAddressService.updateById(address));
    }

    @ApiOperation(value = "删除")
    @GetMapping("/del")
    public Wrapper<Boolean> del(SysUserAddress address) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, sysUserAddressService.removeById(address.getId()));
    }

}
