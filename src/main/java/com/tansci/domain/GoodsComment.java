package com.tansci.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @path：com.tansci.domain.GoodsClassify.java
 * @className：GoodsClassify.java
 * @description：商品评价
 * @author：tanyp
 * @dateTime：2022/03/29 10:38
 * @editNote：
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "goods_comment")
@ApiModel(value = "商品评价")
public class GoodsComment {

    @ApiModelProperty(value = "评论id")
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    @ApiModelProperty(value = "商品id")
    private String goodsId;

    @ApiModelProperty(value = "商户id")
    private String userId;

    @ApiModelProperty(value = "评价时间")
    private LocalDate date;

    @ApiModelProperty(value = "评分")
    private String rating;

    @ApiModelProperty(value = "评语")
    private String content;

    @ApiModelProperty(value = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private LocalDateTime createTime;

}
