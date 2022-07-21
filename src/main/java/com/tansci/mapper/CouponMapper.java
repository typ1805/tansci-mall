package com.tansci.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tansci.domain.Coupon;
import org.apache.ibatis.annotations.Mapper;

/**
 * @path：com.tansci.mapper.CouponMapper.java
 * @className：CouponMapper.java
 * @description：优惠券
 * @author：tanyp
 * @dateTime：2022/7/21 10:56 
 * @editNote：
 */
@Mapper
public interface CouponMapper extends BaseMapper<Coupon> {
}
