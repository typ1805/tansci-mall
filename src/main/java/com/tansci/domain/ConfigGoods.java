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

import java.time.LocalDateTime;

/**
 * @path：com.tansci.domain.ConfigGoods.java
 * @className：ConfigGoods.java
 * @description：商品配置
 * @author：tanyp
 * @dateTime：2022/03/29 10:38
 * @editNote：
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "config_goods")
@ApiModel(value = "商品配置")
public class ConfigGoods {

    @ApiModelProperty(value = "主键ID")
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    @ApiModelProperty(value = "商品id")
    private String goodsId;

    @ApiModelProperty(value = "类型：1、秒杀，2、排行榜")
    private Integer type;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "配置描述")
    private String name;

    @ApiModelProperty(value = "商户id")
    private String userId;

    @ApiModelProperty(value = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "商品信息")
    @TableField(exist = false)
    private Goods goods;

}
