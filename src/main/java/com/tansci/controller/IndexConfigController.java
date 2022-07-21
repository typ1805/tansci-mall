package com.tansci.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tansci.common.WrapMapper;
import com.tansci.common.Wrapper;
import com.tansci.domain.IndexConfig;
import com.tansci.service.IndexConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * @path：com.tansci.controller.IndexConfigController.java
 * @className：IndexConfigController.java
 * @description： 首页标签配置
 * @author：tanyp
 * @dateTime：2022/7/21 13:14 
 * @editNote：
 */
@Slf4j
@RestController
@RequestMapping("/indexConfig")
@Api(value = "indexConfig", tags = "首页标签配置")
public class IndexConfigController {

    @Autowired
    private IndexConfigService indexConfigService;

    @ApiOperation(value = "分页", notes = "分页")
    @GetMapping("/page")
    public Wrapper<IPage<IndexConfig>> page(Page page, IndexConfig config) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, indexConfigService.page(page, config));
    }

    @ApiOperation(value = "添加", notes = "添加")
    @PostMapping("/save")
    public Wrapper<Object> save(@RequestBody IndexConfig config) {
        config.setUpdateTime(LocalDateTime.now());
        config.setCreateTime(LocalDateTime.now());
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, indexConfigService.save(config));
    }

    @ApiOperation(value = "更新", notes = "更新")
    @PostMapping("/update")
    public Wrapper<Object> update(@RequestBody IndexConfig config) {
        config.setUpdateTime(LocalDateTime.now());
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, indexConfigService.updateById(config));
    }

    @ApiOperation(value = "删除", notes = "删除")
    @GetMapping("/delete")
    public Wrapper<Object> delete(IndexConfig config) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, indexConfigService.removeById(config.getId()));
    }

}
