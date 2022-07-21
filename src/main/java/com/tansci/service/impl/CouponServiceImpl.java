package com.tansci.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tansci.domain.Coupon;
import com.tansci.mapper.CouponMapper;
import com.tansci.service.CouponService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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

    @Override
    public IPage<Coupon> page(Page page, Coupon coupon) {
        IPage<Coupon> iPage = this.baseMapper.selectPage(page,
                Wrappers.<Coupon>lambdaQuery()
                        .orderByDesc(Coupon::getUpdateTime)
        );
        return iPage;
    }

}
