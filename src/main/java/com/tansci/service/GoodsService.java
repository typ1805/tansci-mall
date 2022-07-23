package com.tansci.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tansci.domain.Goods;

/**
 * @path：com.tansci.service.GoodsService.java
 * @className：GoodsService.java
 * @description：商品
 * @author：tanyp
 * @dateTime：2022/03/29 10:38
 * @editNote：
 */
public interface GoodsService extends IService<Goods> {

    IPage<Goods> page(Page page, Goods goods);

    boolean save(Goods goods);

    Object update(Goods goods);

    Object delete(Goods goods);

}
