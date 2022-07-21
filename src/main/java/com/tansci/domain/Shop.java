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
 * @path：com.tansci.domain.Shop.java
 * @className：Shop.java
 * @description：店铺
 * @author：tanyp
 * @dateTime：2022/03/29 10:38
 * @editNote：
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "shop")
@ApiModel(value = "店铺")
public class Shop {

    @ApiModelProperty(value = "店铺id")
    @TableId(type = IdType.ASSIGN_UUID)
    private String shopId;

    @ApiModelProperty(value = "店铺名称")
    private String name;

    @ApiModelProperty(value = "店铺介绍")
    private String intro;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "店铺星级")
    private Integer level;

    @ApiModelProperty(value = "状态：0、正常，1、下线")
    private Integer status;

    @ApiModelProperty(value = "商户id")
    private String userId;

    @ApiModelProperty(value = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "备注")
    private String remarks;

}
