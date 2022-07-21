package com.tansci.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tansci.domain.Refund;
import com.tansci.mapper.RefundMapper;
import com.tansci.service.RefundService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @path：com.tansci.service.impl.RefundServiceImpl.java
 * @className：RefundServiceImpl.java
 * @description： 退款
 * @author：tanyp
 * @dateTime：2022/7/21 13:17 
 * @editNote：
 */
@Slf4j
@Service
public class RefundServiceImpl extends ServiceImpl<RefundMapper, Refund> implements RefundService {

    @Override
    public IPage<Refund> page(Page page, Refund refund) {
        IPage<Refund> iPage = this.baseMapper.selectPage(page,
                Wrappers.<Refund>lambdaQuery()
                        .eq(Objects.nonNull(refund.getRefundStatus()), Refund::getRefundStatus, refund.getRefundStatus())
                        .eq(Objects.nonNull(refund.getOrderId()), Refund::getOrderId, refund.getOrderId())
                        .eq(Objects.nonNull(refund.getGoodsOrderId()), Refund::getGoodsOrderId, refund.getGoodsOrderId())
                        .eq(Objects.nonNull(refund.getUserId()), Refund::getUserId, refund.getUserId())
                        .orderByDesc(Refund::getUpdateTime)
        );
        return iPage;
    }

    @Override
    public boolean save(Refund refund) {
        return false;
    }

    @Override
    public Object update(Refund refund) {
        return null;
    }

    @Override
    public Object delete(Refund refund) {
        return null;
    }
}
