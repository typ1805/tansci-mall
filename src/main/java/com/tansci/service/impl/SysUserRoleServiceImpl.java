package com.tansci.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tansci.domain.SysUserRole;
import com.tansci.mapper.SysUserRoleMapper;
import com.tansci.service.SysUserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @ClassName： SysUserRoleServiceImpl.java
 * @ClassPath： com.tansci.service.impl.SysUserRoleServiceImpl.java
 * @Description： 用户角色
 * @Author： tanyp
 * @Date： 2022/4/25 16:30
 **/
@Slf4j
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService {

    @Override
    public Object userRoleSave(SysUserRole sysUserRole) {
        this.baseMapper.delete(Wrappers.<SysUserRole>lambdaUpdate().eq(SysUserRole::getUserId, sysUserRole.getUserId()));
        return this.baseMapper.insert(sysUserRole);
    }

}
