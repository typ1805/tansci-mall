package com.tansci.controller.api;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tansci.common.WrapMapper;
import com.tansci.common.Wrapper;
import com.tansci.domain.ConfigGoods;
import com.tansci.domain.IndexCarousel;
import com.tansci.domain.IndexConfig;
import com.tansci.service.ConfigGoodsService;
import com.tansci.service.IndexCarouselService;
import com.tansci.service.IndexConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @path：com.tansci.controller.IndexApiController.java
 * @className：IndexApiController.java
 * @description： 首页API
 * @author：tanyp
 * @dateTime：2022/7/21 13:12
 * @editNote：
 */
@Slf4j
@RestController
@RequestMapping("/api/indexApi")
@Api(value = "/api/indexApi", tags = "首页API")
public class IndexApiController {

    @Autowired
    private IndexConfigService indexConfigService;
    @Autowired
    private IndexCarouselService indexCarouselService;
    @Autowired
    private ConfigGoodsService seckillGoodsService;

    @ApiOperation(value = "首页配置分页", notes = "首页配置分页")
    @GetMapping("/getIndexConfigPage")
    public Wrapper<IPage<IndexConfig>> getIndexConfigPage(Page page, IndexConfig config) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, indexConfigService.page(page, config));
    }

    @ApiOperation(value = "首页轮播分页", notes = "首页轮播分页")
    @GetMapping("/getIndexCarouselPage")
    public Wrapper<IPage<IndexCarousel>> getIndexCarouselPage(Page page, IndexCarousel carousel) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, indexCarouselService.page(page, carousel));
    }

    @ApiOperation(value = "配置商品分页", notes = "配置商品分页")
    @GetMapping("/getConfigGoodsPage")
    public Wrapper<IPage<ConfigGoods>> getConfigGoodsPage(Page page, ConfigGoods goods) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, seckillGoodsService.page(page, goods));
    }

}
