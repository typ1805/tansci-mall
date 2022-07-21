package com.tansci.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tansci.domain.GoodsClassify;
import com.tansci.domain.SysDic;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @path：com.tansci.mapper.GoodsClassifyMapper.java
 * @className：GoodsClassifyMapper.java
 * @description：商品分类
 * @author：tanyp
 * @dateTime：2022/7/21 10:58 
 * @editNote：
 */
@Mapper
public interface GoodsClassifyMapper extends BaseMapper<GoodsClassify> {

    List<GoodsClassify> getGoodsClassifyChildrens(String id);

}
