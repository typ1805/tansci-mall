package com.tansci.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tansci.domain.GoodsOrder;
import com.tansci.domain.dto.GoodsOrderDto;

/**
 * @ClassName： GoodsOrderService.java
 * @ClassPath： com.tansci.service.GoodsOrderService.java
 * @Description： 订单
 * @Author： tanyp
 * @Date： 2022/3/29 13:02
 **/
public interface GoodsOrderService extends IService<GoodsOrder> {

    IPage<GoodsOrder> page(Page page, GoodsOrderDto dto);

}
