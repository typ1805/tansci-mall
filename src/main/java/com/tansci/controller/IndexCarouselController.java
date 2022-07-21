package com.tansci.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tansci.common.WrapMapper;
import com.tansci.common.Wrapper;
import com.tansci.domain.IndexCarousel;
import com.tansci.service.IndexCarouselService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * @path：com.tansci.controller.IndexCarouselController.java
 * @className：IndexCarouselController.java
 * @description： 首页轮播
 * @author：tanyp
 * @dateTime：2022/7/21 13:12 
 * @editNote：
 */
@Slf4j
@RestController
@RequestMapping("/indexCarousel")
@Api(value = "indexCarousel", tags = "首页轮播")
public class IndexCarouselController {

    @Autowired
    private IndexCarouselService indexCarouselService;

    @ApiOperation(value = "分页", notes = "分页")
    @GetMapping("/page")
    public Wrapper<IPage<IndexCarousel>> page(Page page, IndexCarousel carousel) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, indexCarouselService.page(page, carousel));
    }

    @ApiOperation(value = "添加", notes = "添加")
    @PostMapping("/save")
    public Wrapper<Object> save(@RequestBody IndexCarousel carousel) {
        carousel.setUpdateTime(LocalDateTime.now());
        carousel.setCreateTime(LocalDateTime.now());
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, indexCarouselService.save(carousel));
    }

    @ApiOperation(value = "更新", notes = "更新")
    @PostMapping("/update")
    public Wrapper<Object> update(@RequestBody IndexCarousel carousel) {
        carousel.setUpdateTime(LocalDateTime.now());
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, indexCarouselService.updateById(carousel));
    }

    @ApiOperation(value = "删除", notes = "删除")
    @GetMapping("/delete")
    public Wrapper<Object> delete(IndexCarousel carousel) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, indexCarouselService.removeById(carousel.getId()));
    }

}
