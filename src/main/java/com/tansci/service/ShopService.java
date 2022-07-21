package com.tansci.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tansci.domain.Shop;

/**
 * @path：com.tansci.service.ShopService.java
 * @className：ShopService.java
 * @description： 店铺
 * @author：tanyp
 * @dateTime：2022/7/21 13:23 
 * @editNote：
 */
public interface ShopService extends IService<Shop> {

    IPage<Shop> page(Page page, Shop shop);

    Object delete(Shop shop);

}
