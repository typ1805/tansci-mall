package com.tansci.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tansci.domain.Refund;

/**
 * @path：com.tansci.service.RefundService.java
 * @className：RefundService.java
 * @description： 退款
 * @author：tanyp
 * @dateTime：2022/7/21 13:17 
 * @editNote：
 */
public interface RefundService extends IService<Refund> {

    IPage<Refund> page(Page page, Refund refund);

    boolean save(Refund refund);

    Object update(Refund refund);

    Object delete(Refund refund);

}
