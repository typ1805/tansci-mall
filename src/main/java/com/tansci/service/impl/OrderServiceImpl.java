package com.tansci.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tansci.domain.Order;
import com.tansci.mapper.OrderMapper;
import com.tansci.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @path：com.tansci.service.impl.OrderServiceImpl.java
 * @className：OrderServiceImpl.java
 * @description： 订单
 * @author：tanyp
 * @dateTime：2022/7/21 13:15 
 * @editNote：
 */
@Slf4j
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Override
    public IPage<Order> page(Page page, Order order) {
        IPage<Order> iPage = this.baseMapper.selectPage(page,
                Wrappers.<Order>lambdaQuery()
                        .eq(Objects.nonNull(order.getOrderStatus()), Order::getOrderStatus, order.getOrderStatus())
                        .eq(Objects.nonNull(order.getPayStatus()), Order::getPayStatus, order.getPayStatus())
                        .eq(Objects.nonNull(order.getUserId()), Order::getUserId, order.getUserId())
                        .orderByDesc(Order::getUpdateTime)
        );
        return iPage;
    }

    @Override
    public boolean save(Order order) {
        return false;
    }

    @Override
    public Object update(Order order) {
        return null;
    }

    @Override
    public Object delete(Order order) {
        return null;
    }
}
