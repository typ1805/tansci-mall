package com.tansci.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tansci.domain.OrderInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @path：com.tansci.mapper.OrderInfoMapper.java
 * @className：OrderInfoMapper.java
 * @description：订单
 * @author：tanyp
 * @dateTime：2022/7/21 11:02 
 * @editNote：
 */
@Mapper
public interface OrderInfoMapper extends BaseMapper<OrderInfo> {
}
