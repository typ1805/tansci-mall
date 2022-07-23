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
 * @ClassName： OrderInfo.java
 * @ClassPath： com.tansci.domain.OrderInfo.java
 * @Description： 订单
 * @Author： tanyp
 * @Date： 2022/3/29 11:57
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "order_info")
@ApiModel(value = "订单")
public class OrderInfo {

    @ApiModelProperty(value = "订单id")
    @TableId(type = IdType.ASSIGN_UUID)
    private String orderId;

    @ApiModelProperty(value = "订单号")
    private String orderNo;

    @ApiModelProperty(value = "金额")
    private BigDecimal price;

    @ApiModelProperty(value = "优惠金额")
    private BigDecimal discount;

    @ApiModelProperty(value = "支付状态：0、未支付，1、支付成功，2、支付失败")
    private Integer payStatus;

    @ApiModelProperty(value = "支付状态")
    @TableField(exist = false)
    private String payStatusName;

    @ApiModelProperty(value = "订单状态：0、待支付，1、已支付，2、配货完成，3、出库成功，4、交易成功，5、商家关闭，6、超时关闭，7、订单退款")
    private Integer orderStatus;

    @ApiModelProperty(value = "订单状态")
    @TableField(exist = false)
    private String orderStatusName;

    @ApiModelProperty(value = "支付类型： 1、支付宝，2、微信，3、银联")
    private Integer payType;

    @ApiModelProperty(value = "支付类型")
    @TableField(exist = false)
    private String payTypeName;

    @ApiModelProperty(value = "支付时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private LocalDateTime payTime;

    @ApiModelProperty(value = "商户ID")
    private String userId;

    @ApiModelProperty(value = "商户名称")
    @TableField(exist = false)
    private String userName;

    @ApiModelProperty(value = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "备注")
    private String remarks;

}
