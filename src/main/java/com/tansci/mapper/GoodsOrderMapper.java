package com.tansci.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tansci.domain.Goods;
import com.tansci.domain.GoodsOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName： GoodsOrderMapper.java
 * @ClassPath： com.tansci.mapper.GoodsOrderMapper.java
 * @Description： 订单
 * @Author： tanyp
 * @Date： 2022/3/29 13:02
 **/
@Mapper
public interface GoodsOrderMapper extends BaseMapper<GoodsOrder> {

    List<Goods> goodsOrderList(@Param("orderIds") List<String> orderIds);

}
