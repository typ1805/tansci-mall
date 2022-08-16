package com.tansci.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tansci.domain.ConfigGoods;

/**
 * @path：com.tansci.service.ConfigGoodsService.java
 * @className：ConfigGoodsService.java
 * @description： 商品配置
 * @author：tanyp
 * @dateTime：2022/7/21 13:21
 * @editNote：
 */
public interface ConfigGoodsService extends IService<ConfigGoods> {

    IPage<ConfigGoods> page(Page page, ConfigGoods goods);

}
