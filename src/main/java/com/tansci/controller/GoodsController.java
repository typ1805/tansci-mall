package com.tansci.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tansci.common.WrapMapper;
import com.tansci.common.Wrapper;
import com.tansci.domain.Goods;
import com.tansci.domain.GoodsImage;
import com.tansci.service.GoodsImageService;
import com.tansci.service.GoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @Autowired
    private GoodsImageService goodsImageService;

    @ApiOperation(value = "分页", notes = "分页")
    @GetMapping("/page")
    public Wrapper<IPage<Goods>> page(Page page, Goods goods) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, goodsService.page(page, goods));
    }

    @ApiOperation(value = "添加", notes = "添加")
    @PostMapping("/save")
    public Wrapper<Boolean> save(@RequestBody Goods goods) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, goodsService.save(goods));
    }

    @ApiOperation(value = "更新", notes = "更新")
    @PostMapping("/update")
    public Wrapper<Object> update(@RequestBody Goods goods) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, goodsService.update(goods));
    }

    @ApiOperation(value = "删除", notes = "删除")
    @GetMapping("/delete")
    public Wrapper<Object> delete(Goods goods) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, goodsService.delete(goods));
    }

    @ApiOperation(value = "商品轮播图列表", notes = "商品轮播图列表")
    @GetMapping("/goodsImageList")
    public Wrapper<List<GoodsImage>> goodsImageList(GoodsImage image) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, goodsImageService.list(image));
    }

}
