package com.tansci.controller.api;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @path：com.tansci.controller.GoodsCommentApiController.java
 * @className：GoodsCommentApiController.java
 * @description： 商品评价API
 * @author：tanyp
 * @dateTime：2022/7/21 13:08
 * @editNote：
 */
@Slf4j
@RestController
@RequestMapping("/api/goodsComment")
@Api(value = "/api/goodsComment", tags = "商品评价API")
public class GoodsCommentApiController {

    @Autowired
    private GoodsCommentService goodsCommentService;

    @ApiOperation(value = "商品评价分页", notes = "商品评价分页")
    @GetMapping("/getGoodsCommentPage")
    public Wrapper<IPage<GoodsComment>> getGoodsCommentPage(Page page, GoodsComment comment) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, goodsCommentService.page(page, comment));
    }

}
