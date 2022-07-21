package com.tansci.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tansci.domain.Cart;
import org.apache.ibatis.annotations.Mapper;

/**
 * @path：com.tansci.mapper.CartMapper.java
 * @className：CartMapper.java
 * @description：购物车
 * @author：tanyp
 * @dateTime：2022/7/21 10:55 
 * @editNote：
 */
@Mapper
public interface CartMapper extends BaseMapper<Cart> {

}
