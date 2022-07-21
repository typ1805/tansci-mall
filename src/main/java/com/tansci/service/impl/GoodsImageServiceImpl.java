package com.tansci.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tansci.domain.GoodsImage;
import com.tansci.mapper.GoodsImageMapper;
import com.tansci.service.GoodsImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
}
