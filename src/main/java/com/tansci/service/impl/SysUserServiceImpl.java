package com.tansci.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tansci.common.Enums;
import com.tansci.domain.Shop;
import com.tansci.domain.SysLoginLog;
import com.tansci.domain.SysUser;
import com.tansci.domain.SysUserRole;
import com.tansci.domain.dto.SysUserDto;
import com.tansci.domain.vo.SysUserVo;
import com.tansci.exception.BusinessException;
import com.tansci.mapper.SysUserMapper;
import com.tansci.service.ShopService;
import com.tansci.service.SysLoginLogService;
import com.tansci.service.SysUserRoleService;
import com.tansci.service.SysUserService;
import com.tansci.utils.JwtTokenUtils;
import com.tansci.utils.SecurityUtils;
import com.tansci.utils.SystemUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @path：com.tanersci.service.impl.SysUserServiceImpl.java
 * @className：SysUserServiceImpl.java
 * @description：用户
 * @author：tanyp
 * @dateTime：2022/03/29 22:18
 * @editNote：
 */
@Slf4j
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SysLoginLogService sysLoginLogService;

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Autowired
    private ShopService shopService;

    @Override
    public IPage<SysUser> page(Page page, SysUserDto dto) {
        Page<SysUser> sysUserPage = this.page(page,
                Wrappers.<SysUser>lambdaQuery()
                        .like(Objects.nonNull(dto.getUsername()), SysUser::getUsername, dto.getUsername())
                        .like(Objects.nonNull(dto.getNickname()), SysUser::getNickname, dto.getNickname())
                        .orderByDesc(SysUser::getCreateTime)
        );
        if (Objects.nonNull(sysUserPage) && sysUserPage.getRecords().size() > 0) {
            List<SysUserRole> roles = sysUserRoleService.list();
            sysUserPage.getRecords().forEach(item -> {
                Optional<SysUserRole> rOptional = roles.stream().filter(u -> Objects.equals(u.getUserId(), item.getId())).findFirst();
                if (rOptional.isPresent()) {
                    item.setRoleId(rOptional.get().getRoleId());
                }

                if (Objects.nonNull(item.getGender())) {
                    item.setGenderName(Enums.getVlaueByGroup(item.getGender(), "user_gender"));
                }

                if (Objects.nonNull(item.getType())) {
                    item.setTypeName(Enums.getVlaueByGroup(item.getType(), "user_type"));
                }
            });
        }
        return sysUserPage;
    }

    @Override
    public Integer modifyPass(SysUserDto dto) {
        SysUser user = this.baseMapper.selectOne(Wrappers.<SysUser>lambdaQuery().eq(SysUser::getUsername, dto.getUsername()));
        // 验证原始密码
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Boolean flag = passwordEncoder.matches(dto.getOldPassword(), user.getPassword());
        if (Objects.isNull(user) || !flag) {
            throw new BusinessException("原始密码错误，请重新输入！");
        }
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setUpdateTime(LocalDateTime.now());
        return this.baseMapper.updateById(user);
    }

    @Override
    public SysUserVo login(SysUserDto dto, HttpServletRequest request) {
        SysUserVo userVo = SysUserVo.builder().loginTime(LocalDateTime.now()).build();

        SysUser user = this.baseMapper.selectOne(Wrappers.<SysUser>lambdaQuery().eq(SysUser::getUsername, dto.getUsername()));
        if (Objects.isNull(user)) {
            throw new BusinessException("用户名或密码有误！");
        }

        // 是否有店铺信息
        Shop shop = shopService.getOne(Wrappers.<Shop>lambdaQuery().eq(Shop::getUserId, user.getId()));
        if (Objects.nonNull(shop)) {
            user.setShopId(shop.getShopId());
            userVo.setShop(shop);
        }

        UserDetails userDetails = new SecurityUtils(user);
        if (!passwordEncoder.matches(dto.getPassword(), userDetails.getPassword())) {
            throw new BusinessException("用户名或密码有误！");
        }

        try {
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // 创建token
            String token = JwtTokenUtils.createToken(
                    SysUser.builder()
                            .id(user.getId())
                            .username(user.getUsername())
                            .password(user.getPassword())
                            .type(user.getType())
                            .build(), false);
            // 返回信息
            userVo.setNickname(user.getNickname());
            userVo.setUsername(user.getUsername());
            userVo.setType(user.getType());
            userVo.setMember(user.getMember());
            userVo.setIntegral(user.getIntegral());
            userVo.setAvatar(user.getAvatar());
            userVo.setToken(token);

            // 记录登录日志
            sysLoginLogService.save(
                    SysLoginLog.builder()
                            .userId(user.getId())
                            .userName(user.getUsername())
                            .loginTime(userVo.getLoginTime())
                            .browser(SystemUtils.getBrowser(request))
                            .ip(SystemUtils.getIpAddress(request))
                            .os(SystemUtils.getOS(request))
                            .createTime(LocalDateTime.now())
                            .build()
            );
        } catch (Exception e) {
            log.error("=====登录异常:{}", e.getMessage());
            throw new BusinessException("用户名或密码有误！");
        }
        return userVo;
    }

}
