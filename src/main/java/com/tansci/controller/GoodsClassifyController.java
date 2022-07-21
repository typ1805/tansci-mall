package com.tansci.controller;

import com.tansci.common.WrapMapper;
import com.tansci.common.Wrapper;
import com.tansci.domain.GoodsClassify;
import com.tansci.service.GoodsClassifyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @path：com.tansci.controller.GoodsClassifyController.java
 * @className：GoodsClassifyController.java
 * @description： 商品分类
 * @author：tanyp
 * @dateTime：2022/7/21 13:06 
 * @editNote：
 */
@Slf4j
@RestController
@RequestMapping("/goodsClassify")
@Api(value = "goodsClassify", tags = "商品分类")
public class GoodsClassifyController {

    @Autowired
    private GoodsClassifyService goodsClassifyService;

    @ApiOperation(value = "列表", notes = "列表")
    @GetMapping("/list")
    public Wrapper<List<GoodsClassify>> list(GoodsClassify classify) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, goodsClassifyService.list(classify));
    }

    @ApiOperation(value = "添加", notes = "添加")
    @PostMapping("/save")
    public Wrapper<Object> save(@RequestBody GoodsClassify classify) {
        classify.setUpdateTime(LocalDateTime.now());
        classify.setCreateTime(LocalDateTime.now());
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, goodsClassifyService.save(classify));
    }

    @ApiOperation(value = "更新", notes = "更新")
    @PostMapping("/update")
    public Wrapper<Object> update(@RequestBody GoodsClassify classify) {
        classify.setUpdateTime(LocalDateTime.now());
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, goodsClassifyService.updateById(classify));
    }

    @ApiOperation(value = "删除", notes = "删除")
    @GetMapping("/delete")
    public Wrapper<Object> delete(GoodsClassify classify) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, goodsClassifyService.delete(classify));
    }

}
