package com.tansci.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tansci.domain.SysUserMenu;
import com.tansci.mapper.SysUserMenuMapper;
import com.tansci.service.SysUserMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @ClassName： SysUserMenuServiceImpl.java
 * @ClassPath： com.tansci.service.impl.SysUserMenuServiceImpl.java
 * @Description： 用户菜单
 * @Author： tanyp
 * @Date： 2021/7/20 17:03
 **/
@Slf4j
@Service
public class SysUserMenuServiceImpl extends ServiceImpl<SysUserMenuMapper, SysUserMenu> implements SysUserMenuService {
}
