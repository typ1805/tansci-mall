package com.tansci.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tansci.domain.Shop;
import org.apache.ibatis.annotations.Mapper;

/**
 * @path：com.tansci.mapper.ShopMapper.java
 * @className：ShopMapper.java
 * @description：店铺
 * @author：tanyp
 * @dateTime：2022/7/21 11:03 
 * @editNote：
 */
@Mapper
public interface ShopMapper extends BaseMapper<Shop> {
}
