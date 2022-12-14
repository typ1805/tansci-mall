package com.tansci.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tansci.domain.SysUserRole;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName： SysUserRoleMapper.java
 * @ClassPath： com.tansci.mapper.SysUserRoleMapper.java
 * @Description： 用户角色
 * @Author： tanyp
 * @Date： 2022/4/25 16:23
 **/
@Mapper
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {
}
