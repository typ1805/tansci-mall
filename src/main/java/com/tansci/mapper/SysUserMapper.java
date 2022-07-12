package com.tansci.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tansci.domain.SysUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * @path：com.tanersci.mapper.SysUserMapper.java
 * @className：SysUserMapper.java
 * @description：用户表
 * @author：tanyp
 * @dateTime：2022/03/29 10:38
 * @editNote：
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {
}
