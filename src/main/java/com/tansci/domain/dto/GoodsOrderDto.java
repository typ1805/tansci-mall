package com.tansci.domain.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

/**
 * @path：com.tansci.domain.dto.GoodsOrderDto.java
 * @className：GoodsOrderDto.java
 * @description：商品订单DTO
 * @author：tanyp
 * @dateTime：2022/03/29 10:38
 * @editNote：
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "商品订单DTO")
public class GoodsOrderDto {

    @TableId(type = IdType.ASSIGN_UUID)
    private String goodsOrderId;

    @ApiModelProperty(value = "订单id")
    private String orderId;

    @ApiModelProperty(value = "状态：0、未支付，1、支付成功，2、支付失败，3、已退款")
    private Integer status;

    @ApiModelProperty(value = "商品ID")
    private String goodsId;

    @ApiModelProperty(value = "价格")
    private BigDecimal price;

    @ApiModelProperty(value = "优惠金额")
    private BigDecimal discount;

    @ApiModelProperty(value = "商户ID")
    private String userId;

    @ApiModelProperty(value = "店铺ID")
    private String shopId;

    @ApiModelProperty(value = "商品个数")
    private Integer goodsNum;

    @ApiModelProperty(value = "购物车ID")
    private String cartId;

}
