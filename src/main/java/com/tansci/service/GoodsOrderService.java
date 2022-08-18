package com.tansci.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tansci.domain.Goods;
import com.tansci.domain.GoodsOrder;
import com.tansci.domain.dto.GoodsOrderDto;

import java.util.List;

/**
 * @ClassName： GoodsOrderService.java
 * @ClassPath： com.tansci.service.GoodsOrderService.java
 * @Description： 订单
 * @Author： tanyp
 * @Date： 2022/3/29 13:02
 **/
public interface GoodsOrderService extends IService<GoodsOrder> {

    IPage<GoodsOrder> page(Page page, GoodsOrderDto dto);

    /**
     * @MonthName： goodsOrderList
     * @Description： 根据订单id获取商品信息
     * @Author： tanyp
     * @Date： 2022/8/18 15:45
     * @Param：
     * @return：
     **/
    List<Goods> goodsOrderList(List<String> orderIds);

}
