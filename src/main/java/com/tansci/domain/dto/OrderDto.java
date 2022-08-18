package com.tansci.domain.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @path：com.tansci.domain.OrderDto.java
 * @className：OrderDto.java
 * @description：订单DTO
 * @author：tanyp
 * @dateTime：2022/03/29 10:38
 * @editNote：
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "订单DTO")
public class OrderDto {

    @TableId(type = IdType.ASSIGN_UUID)
    private String orderId;

    @ApiModelProperty(value = "订单号")
    private String orderNo;

    @ApiModelProperty(value = "支付状态：0、未支付，1、支付成功，2、支付失败")
    private Integer payStatus;

    @ApiModelProperty(value = "订单状态：0、待支付，1、已支付，2、配货完成，3、出库成功，4、交易成功，5、商家关闭，6、超时关闭，7、订单退款")
    private Integer orderStatus;

    @ApiModelProperty(value = "支付类型： 1、支付宝，2、微信，3、银联")
    private Integer payType;

    @ApiModelProperty(value = "商户名称")
    private String username;

    @ApiModelProperty(value = "商品订单")
    private List<GoodsOrderDto> goodsOrders;

}
