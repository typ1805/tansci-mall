package com.tansci.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tansci.domain.SysUserMenu;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName： SysUserMenuMapper.java
 * @ClassPath： com.tansci.mapper.SysUserMenuMapper.java
 * @Description： 用户菜单
 * @Author： tanyp
 * @Date： 2021/7/20 17:00
 **/
@Mapper
public interface SysUserMenuMapper extends BaseMapper<SysUserMenu> {
}
