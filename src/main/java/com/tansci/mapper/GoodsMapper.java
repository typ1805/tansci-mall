package com.tansci.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tansci.domain.Goods;
import org.apache.ibatis.annotations.Mapper;

/**
 * @path：com.tanersci.mapper.GoodsMapper.java
 * @className：GoodsMapper.java
 * @description：商品
 * @author：tanyp
 * @dateTime：2022/03/29 10:38
 * @editNote：
 */
@Mapper
public interface GoodsMapper extends BaseMapper<Goods> {
}
