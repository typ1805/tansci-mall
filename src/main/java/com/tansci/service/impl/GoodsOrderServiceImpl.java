package com.tansci.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tansci.common.OrderEnum;
import com.tansci.domain.Goods;
import com.tansci.domain.GoodsOrder;
import com.tansci.domain.SysUser;
import com.tansci.domain.dto.GoodsOrderDto;
import com.tansci.mapper.GoodsOrderMapper;
import com.tansci.service.GoodsOrderService;
import com.tansci.service.SysUserService;
import com.tansci.utils.SecurityUserUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

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
    private SysUserService sysUserService;

    @Override
    public IPage<GoodsOrder> page(Page page, GoodsOrderDto dto) {
        if (Objects.isNull(dto.getUserId()) && !Objects.equals(1, SecurityUserUtils.getUser().getType())) {
            dto.setUserId(SecurityUserUtils.getUser().getId());
        }
        Page<GoodsOrder> iPage = this.baseMapper.selectPage(page,
                Wrappers.<GoodsOrder>lambdaQuery()
                        .eq(!Objects.equals(1, SecurityUserUtils.getUser().getType()), GoodsOrder::getUserId, SecurityUserUtils.getUser().getId())
                        .eq(Objects.nonNull(dto.getStatus()), GoodsOrder::getStatus, dto.getStatus())
                        .eq(Objects.nonNull(dto.getOrderId()), GoodsOrder::getOrderId, dto.getOrderId())
                        .eq(Objects.nonNull(dto.getUserId()), GoodsOrder::getUserId, dto.getUserId())
                        .orderByDesc(GoodsOrder::getUpdateTime)
        );
        if (Objects.nonNull(iPage.getRecords()) && iPage.getRecords().size() > 0) {
            List<SysUser> userList = sysUserService.listByIds(iPage.getRecords().stream().map(GoodsOrder::getUserId).distinct().collect(Collectors.toList()));

            iPage.getRecords().forEach(item -> {
                Optional<SysUser> uOptional = userList.stream().filter(u -> Objects.equals(u.getId(), item.getUserId())).findFirst();
                if (uOptional.isPresent()) {
                    item.setUserName(uOptional.get().getUsername());
                }

                item.setStatusName(Objects.nonNull(item.getStatus()) ? OrderEnum.getVlaueByGroup(item.getStatus(), "goods_order_status") : null);
            });
        }
        return iPage;
    }

    @Override
    public List<Goods> goodsOrderList(List<String> orderIds) {
        return this.baseMapper.goodsOrderList(orderIds);
    }

}
