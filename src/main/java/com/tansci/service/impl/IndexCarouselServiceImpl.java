package com.tansci.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tansci.domain.IndexCarousel;
import com.tansci.mapper.IndexCarouselMapper;
import com.tansci.service.IndexCarouselService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @path：com.tansci.service.impl.IndexCarouselServiceImpl.java
 * @className：IndexCarouselServiceImpl.java
 * @description： 首页轮播
 * @author：tanyp
 * @dateTime：2022/7/21 13:11 
 * @editNote：
 */
@Slf4j
@Service
public class IndexCarouselServiceImpl extends ServiceImpl<IndexCarouselMapper, IndexCarousel> implements IndexCarouselService {

    @Override
    public IPage<IndexCarousel> page(Page page, IndexCarousel carousel) {
        IPage<IndexCarousel> iPage = this.baseMapper.selectPage(page,
                Wrappers.<IndexCarousel>lambdaQuery()
                        .eq(Objects.nonNull(carousel.getStatus()), IndexCarousel::getStatus, carousel.getStatus())
                        .orderByAsc(IndexCarousel::getSort)
        );
        return iPage;
    }

}
