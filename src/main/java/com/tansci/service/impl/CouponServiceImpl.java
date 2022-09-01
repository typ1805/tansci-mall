package com.tansci.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tansci.common.Enums;
import com.tansci.domain.Coupon;
import com.tansci.domain.Shop;
import com.tansci.domain.SysUser;
import com.tansci.mapper.CouponMapper;
import com.tansci.service.CouponService;
import com.tansci.service.ShopService;
import com.tansci.service.SysUserService;
import com.tansci.utils.SecurityUserUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

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
    @Autowired
    private ShopService shopService;

    @Override
    public IPage<Coupon> page(Page page, Coupon coupon) {
        if (Objects.isNull(coupon.getUserId()) && !Objects.equals(1, SecurityUserUtils.getUser().getType())) {
            coupon.setUserId(SecurityUserUtils.getUser().getId());
        }
        IPage<Coupon> iPage = this.baseMapper.selectPage(page,
                Wrappers.<Coupon>lambdaQuery()
                        .eq(Objects.nonNull(coupon.getShopId()), Coupon::getShopId, coupon.getShopId())
                        .eq(Objects.nonNull(coupon.getType()), Coupon::getType, coupon.getType())
                        .eq(Objects.nonNull(coupon.getStatus()), Coupon::getStatus, coupon.getStatus())
                        .eq(Objects.nonNull(coupon.getUserId()), Coupon::getUserId, coupon.getUserId())
                        .orderByDesc(Coupon::getUpdateTime)
        );
        if (Objects.nonNull(iPage.getRecords()) && iPage.getRecords().size() > 0) {
            List<Shop> shopList = shopService.listByIds(iPage.getRecords().stream().map(Coupon::getShopId).collect(Collectors.toList()));
            List<SysUser> userList = sysUserService.listByIds(iPage.getRecords().stream().map(Coupon::getUserId).collect(Collectors.toList()));

            iPage.getRecords().forEach(item -> {
                item.setTypeName(Objects.nonNull(item.getType()) ? Enums.getVlaueByGroup(item.getType(), "coupon_type") : null);
                item.setStatusName(Objects.nonNull(item.getStatus()) ? Enums.getVlaueByGroup(item.getStatus(), "coupon_status") : null);

                Optional<Shop> sOptional = shopList.stream().filter(s -> Objects.equals(s.getShopId(), item.getShopId())).findFirst();
                if (sOptional.isPresent()) {
                    item.setShopName(sOptional.get().getName());
                }

                Optional<SysUser> uOptional = userList.stream().filter(s -> Objects.equals(s.getId(), item.getUserId())).findFirst();
                if (uOptional.isPresent()) {
                    item.setUsername(uOptional.get().getUsername());
                }
            });
        }
        return iPage;
    }

    @Override
    public List<Coupon> list(Coupon coupon) {
        if (Objects.isNull(coupon.getUserId()) && !Objects.equals(1, SecurityUserUtils.getUser().getType())) {
            coupon.setUserId(SecurityUserUtils.getUser().getId());
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
