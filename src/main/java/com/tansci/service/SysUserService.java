package com.tansci.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tansci.domain.SysUser;
import com.tansci.domain.dto.SysUserDto;

/**
 * @path：com.tansci.service.SysUserService.java
 * @className：SysUserService.java
 * @description：用户
 * @author：tanyp
 * @dateTime：2022/03/29 22:18
 * @editNote：
 */
public interface SysUserService extends IService<SysUser> {

    IPage<SysUser> page(Page page, SysUserDto dto);

    Integer modifyPass(SysUserDto dto);
}
