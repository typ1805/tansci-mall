package com.tansci.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tansci.domain.GoodsLabel;

import java.util.List;

/**
 * @path：com.tansci.service.GoodsLabelService.java
 * @className：GoodsLabelService.java
 * @description： 商品标签
 * @author：tanyp
 * @dateTime：2022/7/22 17:32 
 * @editNote：
 */
public interface GoodsLabelService extends IService<GoodsLabel> {

    IPage<GoodsLabel> page(Page page, GoodsLabel label);

    List<GoodsLabel> list(GoodsLabel label);

}
