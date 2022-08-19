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

/**
 * @path：com.tansci.domain.Coupon.java
 * @className：Coupon.java
 * @description：优惠券
 * @author：tanyp
 * @dateTime：2022/7/21 10:06
 * @editNote：
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "coupon")
@ApiModel(value = "优惠券")
public class Coupon {

    @ApiModelProperty(value = "购物车id")
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "优惠金额")
    private BigDecimal price;

    @ApiModelProperty(value = "限额")
    private BigDecimal norm;

    @ApiModelProperty(value = "状态：0、正常，1、已领取，2、已使用，3、已失效")
    private Integer status;

    @ApiModelProperty(value = "类型：0、平台券，1、店铺券")
    private Integer type;

    @ApiModelProperty(value = "开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private LocalDateTime startTime;

    @ApiModelProperty(value = "结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private LocalDateTime endTime;

    @ApiModelProperty(value = "店铺id")
    private String shopId;

    @ApiModelProperty(value = "领取人")
    private String userId;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "描述")
    private String remarks;

    @ApiModelProperty(value = "类型名称")
    @TableField(exist = false)
    private String typeName;

    @ApiModelProperty(value = "状态名称")
    @TableField(exist = false)
    private String statusName;

    @ApiModelProperty(value = "商户名称")
    @TableField(exist = false)
    private String username;

}
