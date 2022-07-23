package com.tansci.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tansci.domain.GoodsImage;
import com.tansci.mapper.GoodsImageMapper;
import com.tansci.service.GoodsImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @path：com.tansci.service.impl.GoodsImageServiceImpl.java
 * @className：GoodsImageServiceImpl.java
 * @description： 商品图片
 * @author：tanyp
 * @dateTime：2022/7/21 13:09 
 * @editNote：
 */
@Slf4j
@Service
public class GoodsImageServiceImpl extends ServiceImpl<GoodsImageMapper, GoodsImage> implements GoodsImageService {

    @Override
    public List<GoodsImage> list(GoodsImage image) {
        return this.baseMapper.selectList(
                Wrappers.<GoodsImage>lambdaQuery()
                        .eq(Objects.nonNull(image.getGoodsId()), GoodsImage::getGoodsId, image.getGoodsId())
                        .eq(Objects.nonNull(image.getName()), GoodsImage::getName, image.getName())
                        .orderByDesc(GoodsImage::getUpdateTime)
        );
    }

}
