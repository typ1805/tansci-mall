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

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @ClassName： Refund.java
 * @ClassPath： com.tansci.domain.Refund.java
 * @Description： 退款
 * @Author： tanyp
 * @Date： 2022/3/29 11:57
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "goods_order")
@ApiModel(value = "退款")
public class Refund {

    @ApiModelProperty(value = "退款id")
    @TableId(type = IdType.ASSIGN_UUID)
    private String refundId;

    @ApiModelProperty(value = "退款订单号")
    private String refundNo;

    @ApiModelProperty(value = "订单id")
    private String orderId;

    @ApiModelProperty(value = "商品订单id")
    private String goodsOrderId;

    @ApiModelProperty(value = "退款状态：0、退款中，1、退款成功，2、退款失败，3、退款关闭")
    private Integer refundStatus;

    @ApiModelProperty(value = "退款金额")
    private BigDecimal refundPrice;

    @ApiModelProperty(value = "退款时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private LocalDateTime refundTime;

    @ApiModelProperty(value = "退款原因")
    private String reason;

    @ApiModelProperty(value = "商户ID")
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
