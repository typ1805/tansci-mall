package com.tansci.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tansci.domain.SeckillGoods;
import com.tansci.mapper.SeckillGoodsMapper;
import com.tansci.service.SeckillGoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @path：com.tansci.service.impl.SeckillGoodsServiceImpl.java
 * @className：SeckillGoodsServiceImpl.java
 * @description： 秒杀
 * @author：tanyp
 * @dateTime：2022/7/21 13:21 
 * @editNote：
 */
@Slf4j
@Service
public class SeckillGoodsServiceImpl extends ServiceImpl<SeckillGoodsMapper, SeckillGoods> implements SeckillGoodsService {

    @Override
    public IPage<SeckillGoods> page(Page page, SeckillGoods seckill) {
        IPage<SeckillGoods> iPage = this.baseMapper.selectPage(page,
                Wrappers.<SeckillGoods>lambdaQuery()
                        .eq(Objects.nonNull(seckill.getGoodsId()), SeckillGoods::getGoodsId, seckill.getGoodsId())
                        .eq(Objects.nonNull(seckill.getUserId()), SeckillGoods::getUserId, seckill.getUserId())
                        .orderByAsc(SeckillGoods::getSort)
        );
        return iPage;
    }
}
