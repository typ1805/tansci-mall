package com.tansci.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tansci.domain.IndexConfig;
import com.tansci.mapper.IndexConfigMapper;
import com.tansci.service.IndexConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @path：com.tansci.service.impl.IndexConfigServiceImpl.java
 * @className：IndexConfigServiceImpl.java
 * @description： 首页标签配置
 * @author：tanyp
 * @dateTime：2022/7/21 13:13 
 * @editNote：
 */
@Slf4j
@Service
public class IndexConfigServiceImpl extends ServiceImpl<IndexConfigMapper, IndexConfig> implements IndexConfigService {

    @Override
    public IPage<IndexConfig> page(Page page, IndexConfig config) {
        IPage<IndexConfig> iPage = this.baseMapper.selectPage(page,
                Wrappers.<IndexConfig>lambdaQuery()
                        .eq(Objects.nonNull(config.getStatus()), IndexConfig::getStatus, config.getStatus())
                        .orderByAsc(IndexConfig::getSort)
        );
        return iPage;
    }
}
