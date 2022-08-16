package com.tansci.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tansci.domain.ConfigGoods;
import com.tansci.domain.Goods;
import com.tansci.mapper.ConfigGoodsMapper;
import com.tansci.service.ConfigGoodsService;
import com.tansci.service.GoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @path：com.tansci.service.impl.ConfigGoodsServiceImpl.java
 * @className：ConfigGoodsServiceImpl.java
 * @description： 商品配置
 * @author：tanyp
 * @dateTime：2022/7/21 13:21
 * @editNote：
 */
@Slf4j
@Service
public class ConfigGoodsServiceImpl extends ServiceImpl<ConfigGoodsMapper, ConfigGoods> implements ConfigGoodsService {

    @Autowired
    private GoodsService goodsService;

    @Override
    public IPage<ConfigGoods> page(Page page, ConfigGoods goods) {
        IPage<ConfigGoods> iPage = this.baseMapper.selectPage(page,
                Wrappers.<ConfigGoods>lambdaQuery()
                        .eq(Objects.nonNull(goods.getGoodsId()), ConfigGoods::getGoodsId, goods.getGoodsId())
                        .eq(Objects.nonNull(goods.getUserId()), ConfigGoods::getUserId, goods.getUserId())
                        .eq(Objects.nonNull(goods.getType()), ConfigGoods::getType, goods.getType())
                        .like(Objects.nonNull(goods.getName()), ConfigGoods::getName, goods.getName())
                        .orderByAsc(ConfigGoods::getSort)
        );
        if (Objects.nonNull(iPage.getRecords()) && iPage.getRecords().size() > 0) {
            List<Goods> goodsList = goodsService.listByIds(iPage.getRecords().stream().map(ConfigGoods::getGoodsId).collect(Collectors.toList()));
            iPage.getRecords().forEach(item -> {
                Optional<Goods> optional = goodsList.stream().filter(g -> Objects.equals(g.getGoodsId(), item.getGoodsId())).findFirst();
                if (optional.isPresent()) {
                    item.setGoods(optional.get());
                }
            });
        }
        return iPage;
    }

}
