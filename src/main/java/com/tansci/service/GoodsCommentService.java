package com.tansci.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tansci.domain.GoodsComment;

/**
 * @path：com.tansci.service.GoodsCommentService.java
 * @className：GoodsCommentService.java
 * @description： 商品评价
 * @author：tanyp
 * @dateTime：2022/7/21 13:06 
 * @editNote：
 */
public interface GoodsCommentService extends IService<GoodsComment> {

    IPage<GoodsComment> page(Page page, GoodsComment comment);

}
