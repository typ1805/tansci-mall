package com.tansci.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tansci.common.OrderEnum;
import com.tansci.common.PayEnum;
import com.tansci.domain.Goods;
import com.tansci.domain.GoodsOrder;
import com.tansci.domain.OrderInfo;
import com.tansci.domain.SysUser;
import com.tansci.domain.dto.OrderDto;
import com.tansci.exception.BusinessException;
import com.tansci.mapper.OrderInfoMapper;
import com.tansci.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
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
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private GoodsOrderService goodsOrderService;
    @Autowired
    private CartService cartService;

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

    @Transactional
    @Override
    public Object submit(OrderDto dto) {
        log.info("=========购物车===开始=====......==========");
        // 用户验证
        SysUser user = sysUserService.getOne(Wrappers.<SysUser>lambdaQuery().eq(SysUser::getUsername, dto.getUsername()));
        if (Objects.isNull(user)) {
            log.error("===购物车==支付失败====用户信息有误======");
            throw new BusinessException("登录失效，请重新登录！");
        }

        try {
            // 获取商品信息，计算金额
            AtomicReference<BigDecimal> amount = new AtomicReference<>(BigDecimal.ZERO);
            List<String> carts = new ArrayList<>();
            List<GoodsOrder> goodsOrders = new ArrayList<>();
            dto.getGoodsOrders().forEach(item -> {
                Goods goods = goodsService.getById(item.getGoodsId());

                // 计算金额
                BigDecimal price = goods.getPrice().multiply(new BigDecimal(item.getGoodsNum()));
                amount.set(price);

                // 封装商品订单信息
                goodsOrders.add(
                        GoodsOrder.builder()
                                .goodsId(item.getGoodsId())
                                .goodsNum(item.getGoodsNum())
                                .price(price)
                                .status(0)
                                .userId(user.getId())
                                .updateTime(LocalDateTime.now())
                                .createTime(LocalDateTime.now())
                                .build()
                );

                // 购物车信息
                carts.add(item.getCartId());
            });

            // 创建订单
            OrderInfo order = OrderInfo.builder()
                    .price(amount.get())
                    .discount(BigDecimal.ZERO)
                    .payStatus(PayEnum.PAY_STATUS_UNPAID.getKey())
                    .orderStatus(OrderEnum.ORDER_STATUS_UNPAID.getKey())
                    .payType(dto.getPayType())
                    .userId(user.getId())
                    .updateTime(LocalDateTime.now())
                    .createTime(LocalDateTime.now())
                    .build();
            this.save(order);

            // 创建商品订单
            goodsOrders.forEach(item -> item.setOrderId(order.getOrderId()));
            goodsOrderService.saveBatch(goodsOrders);

            // 删除购物车
            cartService.removeBatchByIds(carts);
            log.info("=========购物车===结束=====支付成功==========");
            return "支付成功！";
        } catch (Exception e) {
            log.error("===购物车==支付失败====异常信息：{}", e);
            throw new BusinessException("支付失败！");
        }
    }

    @Override
    public Object update(OrderInfo order) {
        // todo
        return null;
    }

    @Transactional
    @Override
    public Object delete(OrderInfo order) {
        int row = this.baseMapper.deleteById(order.getOrderId());
        if (row > 0) {
            return goodsOrderService.remove(Wrappers.<GoodsOrder>lambdaQuery().eq(GoodsOrder::getOrderId, order.getOrderId()));
        }
        return row;
    }
}
