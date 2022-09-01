package com.tansci.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tansci.common.OrderEnum;
import com.tansci.common.PayEnum;
import com.tansci.domain.*;
import com.tansci.domain.dto.OrderDto;
import com.tansci.domain.vo.OrderStatusCountVo;
import com.tansci.exception.BusinessException;
import com.tansci.mapper.OrderInfoMapper;
import com.tansci.service.*;
import com.tansci.utils.SecurityUserUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
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
    @Autowired
    private ShopService shopService;

    @Override
    public IPage<OrderInfo> page(Page page, OrderInfo order) {
        if (Objects.isNull(order.getUserId()) && !Objects.equals(1, SecurityUserUtils.getUser().getType())) {
            order.setUserId(SecurityUserUtils.getUser().getId());
        }

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
                    item.setUsername(uOptional.get().getUsername());
                }

                item.setPayStatusName(Objects.nonNull(item.getPayStatus()) ? PayEnum.getVlaueByGroup(item.getPayStatus(), "pay_status") : null);
                item.setPayTypeName(Objects.nonNull(item.getPayType()) ? PayEnum.getVlaueByGroup(item.getPayType(), "pay_type") : null);
                item.setOrderStatusName(Objects.nonNull(item.getOrderStatus()) ? OrderEnum.getVlaueByGroup(item.getOrderStatus(), "order_status") : null);
            });
        }
        return iPage;
    }

    @Override
    public List<OrderInfo> list(OrderInfo order) {
        if (Objects.isNull(order.getUserId()) && !Objects.equals(1, SecurityUserUtils.getUser().getType())) {
            order.setUserId(SecurityUserUtils.getUser().getId());
        }

        List<OrderInfo> orders = this.baseMapper.selectList(
                Wrappers.<OrderInfo>lambdaQuery()
                        .eq(Objects.nonNull(order.getUserId()), OrderInfo::getUserId, order.getUserId())
                        .in(Objects.nonNull(order.getStatus()) && order.getStatus().size() > 0, OrderInfo::getOrderStatus, order.getStatus())
                        .orderByDesc(OrderInfo::getUpdateTime)
        );

        if (Objects.nonNull(orders) && orders.size() > 0) {
            // 商品
            List<Goods> goodsList = goodsOrderService.goodsOrderList(orders.stream().map(OrderInfo::getOrderId).collect(Collectors.toList()));
            // 根据订单分组商品
            Map<String, List<Goods>> goodsMap = goodsList.stream().collect(Collectors.groupingBy(Goods::getOrderId));

            // 店铺
            List<Shop> shops = shopService.listByIds(goodsList.stream().map(Goods::getShopId).collect(Collectors.toList()));


            orders.forEach(item -> {
                // 订单状态
                item.setOrderStatusName(Objects.nonNull(item.getOrderStatus()) ? OrderEnum.getVlaueByGroup(item.getOrderStatus(), "order_status") : null);
                // 商品信息
                List<Goods> _goodsList = goodsMap.get(item.getOrderId());
                item.setGoodsList(_goodsList);
                item.setShopId(_goodsList.get(0).getShopId());
                // 店铺信息
                Optional<Shop> sOptional = shops.stream().filter(s -> Objects.equals(s.getShopId(), _goodsList.get(0).getShopId())).findFirst();
                if (sOptional.isPresent()) {
                    item.setShopName(sOptional.get().getName());
                }
            });
        }

        return orders;
    }

    @Override
    public List<OrderStatusCountVo> getOrderStatusCount(OrderInfo order) {
        if (Objects.isNull(order.getUserId()) && !Objects.equals(1, SecurityUserUtils.getUser().getType())) {
            order.setUserId(SecurityUserUtils.getUser().getId());
        }

        List<OrderStatusCountVo> voList = new ArrayList<>();
        List<OrderStatusCountVo> countVos = this.baseMapper.getOrderStatusCount(order);
        if (Objects.nonNull(countVos) && countVos.size() > 0) {
            voList.add(OrderStatusCountVo.builder().orderNum(countVos.stream().filter(e -> Objects.equals(0, e.getLabel())).mapToInt(OrderStatusCountVo::getOrderNum).sum()).label(0).build());
            voList.add(OrderStatusCountVo.builder().orderNum(countVos.stream().filter(e -> Arrays.asList(1, 2).contains(e.getLabel())).mapToInt(OrderStatusCountVo::getOrderNum).sum()).label(1).build());
            voList.add(OrderStatusCountVo.builder().orderNum(countVos.stream().filter(e -> Objects.equals(3, e.getLabel())).mapToInt(OrderStatusCountVo::getOrderNum).sum()).label(2).build());
            voList.add(OrderStatusCountVo.builder().orderNum(countVos.stream().filter(e -> Objects.equals(4, e.getLabel())).mapToInt(OrderStatusCountVo::getOrderNum).sum()).label(3).build());
        }
        return voList;
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
                amount.updateAndGet(a -> a.add(price));

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
