package com.tansci.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tansci.common.WrapMapper;
import com.tansci.common.Wrapper;
import com.tansci.domain.SeckillGoods;
import com.tansci.service.SeckillGoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * @path：com.tansci.controller.SeckillGoodsController.java
 * @className：SeckillGoodsController.java
 * @description： 秒杀
 * @author：tanyp
 * @dateTime：2022/7/21 13:22 
 * @editNote：
 */
@Slf4j
@RestController
@RequestMapping("/seckillGoods")
@Api(value = "seckillGoods", tags = "秒杀商品")
public class SeckillGoodsController {

    @Autowired
    private SeckillGoodsService seckillGoodsService;

    @ApiOperation(value = "分页", notes = "分页")
    @GetMapping("/page")
    public Wrapper<IPage<SeckillGoods>> page(Page page, SeckillGoods seckill) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, seckillGoodsService.page(page, seckill));
    }

    @ApiOperation(value = "添加", notes = "添加")
    @PostMapping("/save")
    public Wrapper<Object> save(@RequestBody SeckillGoods seckill) {
        seckill.setUpdateTime(LocalDateTime.now());
        seckill.setCreateTime(LocalDateTime.now());
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, seckillGoodsService.save(seckill));
    }

    @ApiOperation(value = "更新", notes = "更新")
    @PostMapping("/update")
    public Wrapper<Object> update(@RequestBody SeckillGoods seckill) {
        seckill.setUpdateTime(LocalDateTime.now());
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, seckillGoodsService.updateById(seckill));
    }

    @ApiOperation(value = "删除", notes = "删除")
    @GetMapping("/delete")
    public Wrapper<Object> delete(SeckillGoods seckill) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, seckillGoodsService.removeById(seckill.getId()));
    }

}
