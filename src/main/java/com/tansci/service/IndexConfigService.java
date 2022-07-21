package com.tansci.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tansci.domain.IndexConfig;

/**
 * @path：com.tansci.service.IndexConfigService.java
 * @className：IndexConfigService.java
 * @description： 首页标签配置
 * @author：tanyp
 * @dateTime：2022/7/21 13:13 
 * @editNote：
 */
public interface IndexConfigService extends IService<IndexConfig> {

    IPage<IndexConfig> page(Page page, IndexConfig config);

}
