package com.tansci.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @ClassName： OrderOverviewVo.java
 * @ClassPath： com.tansci.domain.vo.OrderOverviewVo.java
 * @Description： 交易数据总览
 * @Author： tanyp
 * @Date： 2022/4/2 10:00
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "交易数据总览")
public class OrderOverviewVo {

    @ApiModelProperty(value = "时间")
    private String date;

    @ApiModelProperty(value = "订单总数")
    private Integer orderNum;

    @ApiModelProperty(value = "总成交额")
    private BigDecimal amount;

    @ApiModelProperty(value = "微信成交额")
    private BigDecimal wxAmout;

    @ApiModelProperty(value = "支付宝成交额")
    private BigDecimal aliAmout;

}
