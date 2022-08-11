package com.tansci.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @path：com.tansci.domain.Goods.java
 * @className：Goods.java
 * @description：商品
 * @author：tanyp
 * @dateTime：2022/03/29 10:38
 * @editNote：
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "goods")
@ApiModel(value = "商品")
public class Goods {

    @ApiModelProperty(value = "主键ID")
    @TableId(type = IdType.ASSIGN_UUID)
    private String goodsId;

    @ApiModelProperty(value = "商品名称")
    private String name;

    @ApiModelProperty(value = "商品简介")
    private String intro;

    @ApiModelProperty(value = "状态：0、已下架，1、已上架")
    private Integer status;

    @ApiModelProperty(value = "分类")
    private String classify;

    @ApiModelProperty(value = "价格")
    private BigDecimal price;

    @ApiModelProperty(value = "商品主图")
    private String coverImg;

    @ApiModelProperty(value = "库存")
    private Integer stock;

    @ApiModelProperty(value = "销量")
    private Integer sales;

    @ApiModelProperty(value = "标签：多个以逗号分隔")
    private String labels;

    @ApiModelProperty(value = "商品详情")
    private String details;

    @ApiModelProperty(value = "商户ID")
    private String userId;

    @ApiModelProperty(value = "店铺ID")
    private String shopId;

    @ApiModelProperty(value = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "备注")
    private String remarks;

    @ApiModelProperty(value = "状态名称")
    @TableField(exist = false)
    private String statusName;

    @ApiModelProperty(value = "分类名称")
    @TableField(exist = false)
    private String classifyName;

    @ApiModelProperty(value = "标签列表")
    @TableField(exist = false)
    private List<GoodsLabel> labelList;

    @ApiModelProperty(value = "商户名称")
    @TableField(exist = false)
    private String userName;

    @ApiModelProperty(value = "店铺名称")
    @TableField(exist = false)
    private String shopName;

    @ApiModelProperty(value = "店铺")
    @TableField(exist = false)
    private Shop shop;

    @ApiModelProperty(value = "商品图片")
    @TableField(exist = false)
    private List<GoodsImage> imageList;

}
