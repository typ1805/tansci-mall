package com.tansci.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tansci.domain.GoodsImage;

import java.util.List;

/**
 * @path：com.tansci.service.GoodsImageService.java
 * @className：GoodsImageService.java
 * @description： 商品图片
 * @author：tanyp
 * @dateTime：2022/7/21 13:09 
 * @editNote：
 */
public interface GoodsImageService extends IService<GoodsImage> {

    List<GoodsImage> list(GoodsImage image);

}
