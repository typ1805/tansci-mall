package com.tansci.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tansci.common.WrapMapper;
import com.tansci.common.Wrapper;
import com.tansci.domain.Goods;
import com.tansci.domain.dto.GoodsDto;
import com.tansci.service.GoodsService;
import com.tansci.utils.SecurityUserUtils;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * @path：com.tansci.controller.GoodsController.java
 * @className：GoodsController.java
 * @description：商品
 * @author：tanyp
 * @dateTime：2022/03/29 10:38
 * @editNote：
 */
@Slf4j
@RestController
@RequestMapping("/goods")
@Api(value = "goods", tags = "商品")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @GetMapping("/page")
    public Wrapper<IPage<Goods>> page(Page page, GoodsDto dto) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, goodsService.page(page, dto));
    }

    @PostMapping("/save")
    public Wrapper<Boolean> save(@RequestBody Goods goods) {
        goods.setCreateTime(LocalDateTime.now());
        goods.setUpdateTime(LocalDateTime.now());
        goods.setUserId(SecurityUserUtils.getUser().getId());
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, goodsService.save(goods));
    }

    @PostMapping("/update")
    public Wrapper<Boolean> update(@RequestBody Goods goods) {
        goods.setUpdateTime(LocalDateTime.now());
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, goodsService.updateById(goods));
    }

    @GetMapping("/del")
    public Wrapper<Boolean> del(GoodsDto dto) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, goodsService.del(dto));
    }

}
