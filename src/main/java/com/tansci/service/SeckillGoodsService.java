package com.tansci.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tansci.domain.SeckillGoods;

/**
 * @path：com.tansci.service.SeckillGoodsService.java
 * @className：SeckillGoodsService.java
 * @description： 秒杀
 * @author：tanyp
 * @dateTime：2022/7/21 13:21 
 * @editNote：
 */
public interface SeckillGoodsService extends IService<SeckillGoods> {

    IPage<SeckillGoods> page(Page page, SeckillGoods seckill);

}
