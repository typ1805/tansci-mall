package com.tansci.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tansci.domain.IndexCarousel;

/**
 * @path：com.tansci.service.IndexCarouselService.java
 * @className：IndexCarouselService.java
 * @description： 首页轮播
 * @author：tanyp
 * @dateTime：2022/7/21 13:10 
 * @editNote：
 */
public interface IndexCarouselService extends IService<IndexCarousel> {

    IPage<IndexCarousel> page(Page page, IndexCarousel carousel);

}
