package com.tansci.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tansci.domain.SysMenu;
import com.tansci.domain.SysUserMenu;
import com.tansci.domain.dto.SysMenuDto;
import com.tansci.mapper.SysMenuMapper;
import com.tansci.service.SysMenuService;
import com.tansci.service.SysUserMenuService;
import com.tansci.utils.SecurityUserUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @ClassName： SysMenuServiceImpl.java
 * @ClassPath： com.tansci.service.impl.SysMenuServiceImpl.java
 * @Description： 菜单
 * @Author： tanyp
 * @Date： 2021/7/20 17:05
 **/
@Slf4j
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    @Autowired
    private SysUserMenuService sysUserMenuService;

    @Override
    public List<SysMenu> list(SysMenuDto dto) {
        dto.setUserType(SecurityUserUtils.getUser().getType());
        if (Objects.isNull(dto.getUserId()) && !Objects.equals(1, SecurityUserUtils.getUser().getType())) {
            dto.setUserId(SecurityUserUtils.getUser().getId());
        }
        List<SysMenu> list = this.baseMapper.list(dto);

        if (Objects.nonNull(dto.getUserId())) {
            // 获取所有菜单的父idgit
            List<Integer> parentIds = list.stream().map(SysMenu::getParentId).distinct().collect(Collectors.toList());
            if (Objects.nonNull(parentIds) && parentIds.size() > 0) {
                List<SysMenu> parentList = this.list(
                        Wrappers.<SysMenu>lambdaQuery().in(SysMenu::getId, parentIds)
                                .in(Objects.nonNull(dto.getTypes()), SysMenu::getType, dto.getTypes())
                                .eq(Objects.nonNull(dto.getStatus()), SysMenu::getStatus, dto.getStatus())
                );
                list.addAll(parentList);
            }
        }

        list = list.stream().distinct().collect(Collectors.toList());
        Map<Integer, List<SysMenu>> map = list.stream().collect(Collectors.groupingBy(SysMenu::getParentId, Collectors.toList()));
        list.stream().forEach(item -> item.setChildren(map.get(item.getId())));
        return map.get(0);
    }

    @Transactional
    @Override
    public boolean save(SysMenu sysMenu) {
        sysMenu.setCreateTime(LocalDateTime.now());
        sysMenu.setStatus(1);
        if (Objects.isNull(sysMenu.getParentId())) {
            sysMenu.setParentId(0);
        }

        int row = this.baseMapper.insert(sysMenu);
        return false;
    }

    @Transactional
    @Override
    public boolean del(Integer id) {
        List<Integer> ids = new ArrayList<>();
        ids.add(id);
        List<SysMenu> menuList = this.baseMapper.getMenuChildrens(id);
        if (Objects.nonNull(menuList) && menuList.size() > 0) {
            ids.addAll(menuList.stream().map(SysMenu::getId).collect(Collectors.toList()));
        }
        // 删除菜单
        this.baseMapper.deleteBatchIds(ids);
        // 删除权限
        return sysUserMenuService.remove(Wrappers.<SysUserMenu>lambdaQuery().in(SysUserMenu::getMenuId, ids));
    }

    @Override
    public Boolean userMenuSave(SysMenuDto dto) {
        // 删除旧数据
        sysUserMenuService.remove(Wrappers.<SysUserMenu>lambdaQuery().eq(SysUserMenu::getUserId, dto.getUserId()));

        List<SysUserMenu> list = new ArrayList<>();
        dto.getMenuIds().forEach(id -> {
            list.add(SysUserMenu.builder().userId(dto.getUserId()).menuId(id).build());
        });
        return sysUserMenuService.saveBatch(list);
    }

}
