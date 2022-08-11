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
 * @ClassName： GoodsOrder.java
 * @ClassPath： com.tansci.domain.GoodsOrder.java
 * @Description： 商品订单
 * @Author： tanyp
 * @Date： 2022/3/29 11:57
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "goods_order")
@ApiModel(value = "商品订单")
public class GoodsOrder {

    @ApiModelProperty(value = "商品订单id")
    @TableId(type = IdType.ASSIGN_UUID)
    private String goodsOrderId;

    @ApiModelProperty(value = "订单id")
    private String orderId;

    @ApiModelProperty(value = "商品id")
    private String goodsId;

    @ApiModelProperty(value = "商品数量")
    private Integer goodsNum;

    @ApiModelProperty(value = "状态：0、未支付，1、支付成功，2、支付失败，3、已退款")
    private Integer status;

    @ApiModelProperty(value = "订单状态")
    @TableField(exist = false)
    private String statusName;

    @ApiModelProperty(value = "支付时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private LocalDateTime payTime;

    @ApiModelProperty(value = "金额")
    private BigDecimal price;

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
