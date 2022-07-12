package com.tansci.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tansci.domain.SysMenu;
import com.tansci.domain.dto.SysMenuDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName： SysMenuMapper.java
 * @ClassPath： com.tansci.mapper.SysMenuMapper.java
 * @Description： 菜单
 * @Author： tanyp
 * @Date： 2021/7/20 16:57
 **/
@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    List<SysMenu> list(@Param("dto") SysMenuDto dto);

    List<SysMenu> getMenuChildrens(Integer id);

}
