package com.tansci.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tansci.domain.SeckillGoods;
import org.apache.ibatis.annotations.Mapper;

/**
 * @path：com.tansci.mapper.SeckillGoodsMapper.java
 * @className：SeckillGoodsMapper.java
 * @description：秒杀
 * @author：tanyp
 * @dateTime：2022/7/21 11:03 
 * @editNote：
 */
@Mapper
public interface SeckillGoodsMapper extends BaseMapper<SeckillGoods> {
}
