package com.tansci.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tansci.domain.GoodsComment;
import com.tansci.mapper.GoodsCommentMapper;
import com.tansci.service.GoodsCommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @path：com.tansci.service.impl.GoodsCommentServiceImpl.java
 * @className：GoodsCommentServiceImpl.java
 * @description： 商品评价
 * @author：tanyp
 * @dateTime：2022/7/21 13:07 
 * @editNote：
 */
@Slf4j
@Service
public class GoodsCommentServiceImpl extends ServiceImpl<GoodsCommentMapper, GoodsComment> implements GoodsCommentService {

    @Override
    public IPage<GoodsComment> page(Page page, GoodsComment comment) {
        IPage<GoodsComment> iPage = this.baseMapper.selectPage(page,
                Wrappers.<GoodsComment>lambdaQuery()
                        .eq(Objects.nonNull(comment.getGoodsId()), GoodsComment::getGoodsId, comment.getGoodsId())
                        .eq(Objects.nonNull(comment.getUserId()), GoodsComment::getUserId, comment.getUserId())
                        .orderByDesc(GoodsComment::getUpdateTime)
        );
        return iPage;
    }

}
