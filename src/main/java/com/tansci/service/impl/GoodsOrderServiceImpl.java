package com.tansci.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tansci.common.Enums;
import com.tansci.domain.Goods;
import com.tansci.domain.GoodsOrder;
import com.tansci.domain.SysUser;
import com.tansci.domain.dto.GoodsOrderDto;
import com.tansci.mapper.GoodsOrderMapper;
import com.tansci.service.GoodsOrderService;
import com.tansci.service.GoodsService;
import com.tansci.service.SysUserService;
import com.tansci.utils.SecurityUserUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @ClassName： GoodsOrderServiceImpl.java
 * @ClassPath： com.tansci.service.impl.GoodsOrderServiceImpl.java
 * @Description： 订单
 * @Author： tanyp
 * @Date： 2022/3/29 13:03
 **/
@Slf4j
@Service
public class GoodsOrderServiceImpl extends ServiceImpl<GoodsOrderMapper, GoodsOrder> implements GoodsOrderService {

    @Autowired
    private GoodsService goodsService;
    @Autowired
    private SysUserService sysUserService;

    @Override
    public IPage<GoodsOrder> page(Page page, GoodsOrderDto dto) {
        Page<GoodsOrder> orderPage = this.baseMapper.selectPage(page,
                Wrappers.<GoodsOrder>lambdaQuery()
                        .eq(!Objects.equals(1, SecurityUserUtils.getUser().getType()), GoodsOrder::getUserId, SecurityUserUtils.getUser().getId())
                        .eq(Objects.nonNull(dto.getStatus()), GoodsOrder::getStatus, dto.getStatus())
                        .orderByDesc(GoodsOrder::getUpdateTime)
        );
        if (Objects.nonNull(orderPage) && orderPage.getRecords().size() > 0) {
            List<SysUser> users = sysUserService.list();
            List<Goods> goodsList = goodsService.list();
            orderPage.getRecords().forEach(item -> {
                item.setPayTypeName(Enums.getVlaueByGroup(item.getPayType(), "pay_code_type"));
                item.setStatusName(Enums.getVlaueByGroup(item.getStatus(), "order_status"));
                if (Objects.nonNull(item.getGoodsId())) {
                    Optional<Goods> optional = goodsList.stream().filter(g -> Objects.equals(g.getId(), item.getGoodsId())).findFirst();
                    if (optional.isPresent()) {
                        item.setGoodsName(optional.get().getName());
                    }

                    Optional<SysUser> uOptional = users.stream().filter(u -> Objects.equals(u.getId(), item.getUserId())).findFirst();
                    if (uOptional.isPresent()) {
                        item.setUserName(uOptional.get().getUsername());
                    }
                }
            });
        }
        return orderPage;
    }

}
