package com.tansci.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tansci.domain.GoodsLabel;
import com.tansci.mapper.GoodsLabelMapper;
import com.tansci.service.GoodsLabelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @path：com.tansci.service.impl.GoodsLabelServiceImpl.java
 * @className：GoodsLabelServiceImpl.java
 * @description： 商品标签
 * @author：tanyp
 * @dateTime：2022/7/22 17:34 
 * @editNote：
 */
@Slf4j
@Service
public class GoodsLabelServiceImpl extends ServiceImpl<GoodsLabelMapper, GoodsLabel> implements GoodsLabelService {

    @Override
    public IPage<GoodsLabel> page(Page page, GoodsLabel label) {
        return this.baseMapper.selectPage(page,
                Wrappers.<GoodsLabel>lambdaQuery()
                        .like(Objects.nonNull(label.getName()), GoodsLabel::getName, label.getName())
                        .orderByDesc(GoodsLabel::getUpdateTime)
        );
    }

    @Override
    public List<GoodsLabel> list(GoodsLabel label) {
        return this.baseMapper.selectList(Wrappers.lambdaQuery());
    }

}
