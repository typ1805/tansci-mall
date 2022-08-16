package com.tansci.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tansci.common.WrapMapper;
import com.tansci.common.Wrapper;
import com.tansci.domain.ConfigGoods;
import com.tansci.service.ConfigGoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * @path：com.tansci.controller.ConfigGoodsController.java
 * @className：ConfigGoodsController.java
 * @description： 商品配置
 * @author：tanyp
 * @dateTime：2022/7/21 13:22
 * @editNote：
 */
@Slf4j
@RestController
@RequestMapping("/configGoods")
@Api(value = "configGoods", tags = "商品配置")
public class ConfigGoodsController {

    @Autowired
    private ConfigGoodsService seckillGoodsService;

    @ApiOperation(value = "分页", notes = "分页")
    @GetMapping("/page")
    public Wrapper<IPage<ConfigGoods>> page(Page page, ConfigGoods goods) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, seckillGoodsService.page(page, goods));
    }

    @ApiOperation(value = "添加", notes = "添加")
    @PostMapping("/save")
    public Wrapper<Object> save(@RequestBody ConfigGoods goods) {
        goods.setUpdateTime(LocalDateTime.now());
        goods.setCreateTime(LocalDateTime.now());
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, seckillGoodsService.save(goods));
    }

    @ApiOperation(value = "更新", notes = "更新")
    @PostMapping("/update")
    public Wrapper<Object> update(@RequestBody ConfigGoods goods) {
        goods.setUpdateTime(LocalDateTime.now());
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, seckillGoodsService.updateById(goods));
    }

    @ApiOperation(value = "删除", notes = "删除")
    @GetMapping("/delete")
    public Wrapper<Object> delete(ConfigGoods goods) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, seckillGoodsService.removeById(goods.getId()));
    }

}
