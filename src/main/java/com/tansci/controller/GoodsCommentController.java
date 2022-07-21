package com.tansci.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tansci.common.WrapMapper;
import com.tansci.common.Wrapper;
import com.tansci.domain.GoodsComment;
import com.tansci.service.GoodsCommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * @path：com.tansci.controller.GoodsCommentController.java
 * @className：GoodsCommentController.java
 * @description： 商品评价
 * @author：tanyp
 * @dateTime：2022/7/21 13:08 
 * @editNote：
 */
@Slf4j
@RestController
@RequestMapping("/goodsComment")
@Api(value = "goodsComment", tags = "商品评价")
public class GoodsCommentController {

    @Autowired
    private GoodsCommentService goodsCommentService;

    @ApiOperation(value = "分页", notes = "分页")
    @GetMapping("/page")
    public Wrapper<IPage<GoodsComment>> page(Page page, GoodsComment comment) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, goodsCommentService.page(page, comment));
    }

    @ApiOperation(value = "添加", notes = "添加")
    @PostMapping("/save")
    public Wrapper<Object> save(@RequestBody GoodsComment comment) {
        comment.setUpdateTime(LocalDateTime.now());
        comment.setCreateTime(LocalDateTime.now());
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, goodsCommentService.save(comment));
    }

    @ApiOperation(value = "更新", notes = "更新")
    @PostMapping("/update")
    public Wrapper<Object> update(@RequestBody GoodsComment comment) {
        comment.setUpdateTime(LocalDateTime.now());
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, goodsCommentService.updateById(comment));
    }

    @ApiOperation(value = "删除", notes = "删除")
    @GetMapping("/delete")
    public Wrapper<Object> delete(GoodsComment comment) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, goodsCommentService.removeById(comment.getId()));
    }

}
