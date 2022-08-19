package com.tansci.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tansci.common.Enums;
import com.tansci.domain.Coupon;
import com.tansci.domain.SysUser;
import com.tansci.mapper.CouponMapper;
import com.tansci.service.CouponService;
import com.tansci.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @path：com.tansci.service.impl.CouponServiceImpl.java
 * @className：CouponServiceImpl.java
 * @description： 购物车
 * @author：tanyp
 * @dateTime：2022/7/21 13:02
 * @editNote：
 */
@Slf4j
@Service
public class CouponServiceImpl extends ServiceImpl<CouponMapper, Coupon> implements CouponService {

    @Autowired
    private SysUserService sysUserService;

    @Override
    public IPage<Coupon> page(Page page, Coupon coupon) {
        IPage<Coupon> iPage = this.baseMapper.selectPage(page,
                Wrappers.<Coupon>lambdaQuery()
                        .eq(Objects.nonNull(coupon.getShopId()), Coupon::getShopId, coupon.getShopId())
                        .eq(Objects.nonNull(coupon.getType()), Coupon::getType, coupon.getType())
                        .eq(Objects.nonNull(coupon.getStatus()), Coupon::getStatus, coupon.getStatus())
                        .eq(Objects.nonNull(coupon.getUserId()), Coupon::getUserId, coupon.getUserId())
                        .orderByDesc(Coupon::getUpdateTime)
        );
        if (Objects.nonNull(iPage.getRecords()) && iPage.getRecords().size() > 0) {
            iPage.getRecords().forEach(item -> {
                item.setTypeName(Objects.nonNull(item.getType()) ? Enums.getVlaueByGroup(item.getType(), "coupon_type") : null);
                item.setStatusName(Objects.nonNull(item.getStatus()) ? Enums.getVlaueByGroup(item.getStatus(), "coupon_status") : null);
            });
        }
        return iPage;
    }

    @Override
    public List<Coupon> list(Coupon coupon) {
        if (Objects.nonNull(coupon.getUsername())) {
            SysUser user = sysUserService.getOne(Wrappers.<SysUser>lambdaQuery().eq(SysUser::getUsername, coupon.getUsername()));
            coupon.setUserId(user.getId());
        }
        List<Coupon> list = this.baseMapper.selectList(
                Wrappers.<Coupon>lambdaQuery()
                        .eq(Objects.nonNull(coupon.getShopId()), Coupon::getShopId, coupon.getShopId())
                        .eq(Objects.nonNull(coupon.getType()), Coupon::getType, coupon.getType())
                        .eq(Objects.nonNull(coupon.getStatus()), Coupon::getStatus, coupon.getStatus())
                        .eq(Objects.nonNull(coupon.getUserId()), Coupon::getUserId, coupon.getUserId())
                        .orderByDesc(Coupon::getUpdateTime)
        );
        if (Objects.nonNull(list) && list.size() > 0) {
            list.forEach(item -> {
                item.setTypeName(Objects.nonNull(item.getType()) ? Enums.getVlaueByGroup(item.getType(), "coupon_type") : null);
                item.setStatusName(Objects.nonNull(item.getStatus()) ? Enums.getVlaueByGroup(item.getStatus(), "coupon_status") : null);
            });
        }
        return list;
    }

}
