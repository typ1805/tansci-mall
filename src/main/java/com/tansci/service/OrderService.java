package com.tansci.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tansci.domain.Order;

/**
 * @path：com.tansci.service.OrderService.java
 * @className：OrderService.java
 * @description： 订单
 * @author：tanyp
 * @dateTime：2022/7/21 13:15 
 * @editNote：
 */
public interface OrderService extends IService<Order> {

    IPage<Order> page(Page page, Order order);

    boolean save(Order order);

    Object update(Order order);

    Object delete(Order order);

}
