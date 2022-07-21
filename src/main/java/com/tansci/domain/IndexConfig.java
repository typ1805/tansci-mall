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

import java.time.LocalDateTime;

/**
 * @path：com.tansci.domain.IndexConfig.java
 * @className：IndexConfig.java
 * @description：首页标签配置
 * @author：tanyp
 * @dateTime：2022/03/29 10:38
 * @editNote：
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "index_config")
@ApiModel(value = "首页标签配置")
public class IndexConfig {

    @ApiModelProperty(value = "主键ID")
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    @ApiModelProperty(value = "标签名称")
    private String name;

    @ApiModelProperty(value = "连接地址")
    private String url;

    @ApiModelProperty(value = "图标")
    private String icon;

    @ApiModelProperty(value = "色值")
    private String color;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "状态：0：正常，1：下线")
    private Integer status;

    @ApiModelProperty(value = "商品分类")
    private String goodsClassify;

    @ApiModelProperty(value = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private LocalDateTime createTime;

}
