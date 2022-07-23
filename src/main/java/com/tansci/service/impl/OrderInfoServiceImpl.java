package com.tansci.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tansci.common.OrderEnum;
import com.tansci.common.PayEnum;
import com.tansci.domain.OrderInfo;
import com.tansci.domain.SysUser;
import com.tansci.mapper.OrderInfoMapper;
import com.tansci.service.OrderInfoService;
import com.tansci.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @path：com.tansci.service.impl.OrderInfoServiceImpl.java
 * @className：OrderInfoServiceImpl.java
 * @description： 订单
 * @author：tanyp
 * @dateTime：2022/7/21 13:15 
 * @editNote：
 */
@Slf4j
@Service
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoMapper, OrderInfo> implements OrderInfoService {

    @Autowired
    private SysUserService sysUserService;

    @Override
    public IPage<OrderInfo> page(Page page, OrderInfo order) {
        IPage<OrderInfo> iPage = this.baseMapper.selectPage(page,
                Wrappers.<OrderInfo>lambdaQuery()
                        .like(Objects.nonNull(order.getOrderId()), OrderInfo::getOrderId, order.getOrderId())
                        .like(Objects.nonNull(order.getOrderNo()), OrderInfo::getOrderNo, order.getOrderNo())
                        .eq(Objects.nonNull(order.getOrderStatus()), OrderInfo::getOrderStatus, order.getOrderStatus())
                        .eq(Objects.nonNull(order.getPayStatus()), OrderInfo::getPayStatus, order.getPayStatus())
                        .eq(Objects.nonNull(order.getUserId()), OrderInfo::getUserId, order.getUserId())
                        .orderByDesc(OrderInfo::getUpdateTime)
        );
        if (Objects.nonNull(iPage.getRecords()) && iPage.getRecords().size() > 0) {
            List<SysUser> userList = sysUserService.listByIds(iPage.getRecords().stream().map(OrderInfo::getUserId).distinct().collect(Collectors.toList()));

            iPage.getRecords().forEach(item -> {
                Optional<SysUser> uOptional = userList.stream().filter(u -> Objects.equals(u.getId(), item.getUserId())).findFirst();
                if (uOptional.isPresent()) {
                    item.setUserName(uOptional.get().getUsername());
                }

                item.setPayStatusName(Objects.nonNull(item.getPayStatus()) ? PayEnum.getVlaueByGroup(item.getPayStatus(), "pay_status") : null);
                item.setPayTypeName(Objects.nonNull(item.getPayType()) ? PayEnum.getVlaueByGroup(item.getPayType(), "pay_type") : null);
                item.setOrderStatusName(Objects.nonNull(item.getOrderStatus()) ? OrderEnum.getVlaueByGroup(item.getOrderStatus(), "order_status") : null);
            });
        }
        return iPage;
    }

    @Override
    public boolean save(OrderInfo order) {
        return false;
    }

    @Override
    public Object update(OrderInfo order) {
        return null;
    }

    @Override
    public Object delete(OrderInfo order) {
        return null;
    }
}
