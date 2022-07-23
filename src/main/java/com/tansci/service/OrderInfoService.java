package com.tansci.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tansci.domain.OrderInfo;

/**
 * @path：com.tansci.service.OrderInfoService.java
 * @className：OrderInfoService.java
 * @description： 订单
 * @author：tanyp
 * @dateTime：2022/7/21 13:15 
 * @editNote：
 */
public interface OrderInfoService extends IService<OrderInfo> {

    IPage<OrderInfo> page(Page page, OrderInfo order);

    boolean save(OrderInfo order);

    Object update(OrderInfo order);

    Object delete(OrderInfo order);

}
