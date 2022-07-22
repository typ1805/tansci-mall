package com.tansci.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tansci.common.WrapMapper;
import com.tansci.common.Wrapper;
import com.tansci.domain.GoodsLabel;
import com.tansci.service.GoodsLabelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @path：com.tansci.controller.GoodsLabelController.java
 * @className：GoodsLabelController.java
 * @description： 商品标签
 * @author：tanyp
 * @dateTime：2022/7/22 17:36 
 * @editNote：
 */
@Slf4j
@RestController
@RequestMapping("/goodsLabel")
@Api(value = "goodsLabel", tags = "商品标签")
public class GoodsLabelController {

    @Autowired
    private GoodsLabelService goodsLabelService;

    @ApiOperation(value = "分页", notes = "分页")
    @GetMapping("/page")
    public Wrapper<IPage<GoodsLabel>> page(Page page, GoodsLabel label) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, goodsLabelService.page(page, label));
    }

    @ApiOperation(value = "列表", notes = "列表")
    @GetMapping("/list")
    public Wrapper<List<GoodsLabel>> list(GoodsLabel label) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, goodsLabelService.list(label));
    }

    @ApiOperation(value = "添加", notes = "添加")
    @PostMapping("/save")
    public Wrapper<Boolean> save(@RequestBody GoodsLabel label) {
        label.setCreateTime(LocalDateTime.now());
        label.setUpdateTime(LocalDateTime.now());
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, goodsLabelService.save(label));
    }

    @ApiOperation(value = "更新", notes = "更新")
    @PostMapping("/update")
    public Wrapper<Boolean> update(@RequestBody GoodsLabel label) {
        label.setUpdateTime(LocalDateTime.now());
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, goodsLabelService.updateById(label));
    }

    @ApiOperation(value = "删除", notes = "删除")
    @GetMapping("/delete")
    public Wrapper<Boolean> delete(GoodsLabel label) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, goodsLabelService.removeById(label.getId()));
    }

}
