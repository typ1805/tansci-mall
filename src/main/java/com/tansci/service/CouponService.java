package com.tansci.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tansci.domain.Coupon;

/**
 * @path：com.tansci.service.CouponService.java
 * @className：CouponService.java
 * @description： 优惠券
 * @author：tanyp
 * @dateTime：2022/7/21 13:02 
 * @editNote：
 */
public interface CouponService extends IService<Coupon> {

    IPage<Coupon> page(Page page, Coupon coupon);

}
