package com.tansci.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.tansci.domain.Goods;
import com.tansci.domain.GoodsOrder;
import com.tansci.domain.dto.ApiDto;
import com.tansci.exception.BusinessException;
import com.tansci.service.ApiService;
import com.tansci.service.GoodsOrderService;
import com.tansci.service.GoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @path：com.tansci.service.impl.ApiServiceImpl.java
 * @className：ApiServiceImpl.java
 * @description：API接口
 * @author：tanyp
 * @dateTime：2022/3/29 20:38
 * @editNote：
 */
@Slf4j
@Service
public class ApiServiceImpl implements ApiService {

    @Autowired
    private GoodsService goodsService;
    @Autowired
    private GoodsOrderService orderService;

    @Override
    public List<Goods> goodsList(String userId) {
        if (Objects.isNull(userId)) {
            return new ArrayList<>();
        }

        List<Goods> goodsList = goodsService.list(
                Wrappers.<Goods>lambdaQuery().eq(Goods::getUserId, userId).eq(Goods::getStatus, 1).orderByDesc(Goods::getUpdateTime)
        );
        return goodsList;
    }

    @Override
    public Object payment(ApiDto dto) {
        Goods goods = goodsService.getById(dto.getGoodsId());
        if (Objects.isNull(goods)) {
            throw new BusinessException("商品不存在!");
        }
        GoodsOrder order = GoodsOrder.builder()
                .goodsId(goods.getId())
                .status(0)
                .payDesc("待支付")
                .payType(dto.getType())
                .amount(goods.getPrice())
                .userId(goods.getUserId())
                .updateTime(LocalDateTime.now())
                .createTime(LocalDateTime.now())
                .build();
        orderService.save(order);
        return order.getId();
    }

}
