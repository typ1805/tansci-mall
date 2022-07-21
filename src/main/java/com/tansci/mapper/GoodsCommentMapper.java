package com.tansci.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tansci.domain.GoodsComment;
import org.apache.ibatis.annotations.Mapper;

/**
 * @path：com.tansci.mapper.GoodsCommentMapper.java
 * @className：GoodsCommentMapper.java
 * @description：商品评价
 * @author：tanyp
 * @dateTime：2022/7/21 10:59 
 * @editNote：
 */
@Mapper
public interface GoodsCommentMapper extends BaseMapper<GoodsComment> {
}
