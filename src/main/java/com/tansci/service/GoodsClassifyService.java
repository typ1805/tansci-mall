package com.tansci.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tansci.domain.GoodsClassify;

import java.util.List;

/**
 * @path：com.tansci.service.GoodsClassifyService.java
 * @className：GoodsClassifyService.java
 * @description： 商品分类
 * @author：tanyp
 * @dateTime：2022/7/21 13:04 
 * @editNote：
 */
public interface GoodsClassifyService extends IService<GoodsClassify> {

    List<GoodsClassify> list(GoodsClassify classify);

    Object delete(GoodsClassify classify);

}
