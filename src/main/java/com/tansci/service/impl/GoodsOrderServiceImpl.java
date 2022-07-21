package com.tansci.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tansci.domain.GoodsOrder;
import com.tansci.domain.dto.GoodsOrderDto;
import com.tansci.mapper.GoodsOrderMapper;
import com.tansci.service.GoodsOrderService;
import com.tansci.utils.SecurityUserUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

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

    @Override
    public IPage<GoodsOrder> page(Page page, GoodsOrderDto dto) {
        Page<GoodsOrder> orderPage = this.baseMapper.selectPage(page,
                Wrappers.<GoodsOrder>lambdaQuery()
                        .eq(!Objects.equals(1, SecurityUserUtils.getUser().getType()), GoodsOrder::getUserId, SecurityUserUtils.getUser().getId())
                        .eq(Objects.nonNull(dto.getStatus()), GoodsOrder::getStatus, dto.getStatus())
                        .orderByDesc(GoodsOrder::getUpdateTime)
        );
        return orderPage;
    }

}
