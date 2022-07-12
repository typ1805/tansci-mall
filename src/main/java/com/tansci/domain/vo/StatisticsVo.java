package com.tansci.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @ClassName： StatisticsVo.java
 * @ClassPath： com.tansci.domain.vo.StatisticsVo.java
 * @Description： 平台数据统计
 * @Author： tanyp
 * @Date： 2022/4/2 9:01
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "平台数据统计")
public class StatisticsVo {

    @ApiModelProperty(value = "订单总数（个）")
    private Integer orderNum;

    @ApiModelProperty(value = "成交额（元）")
    private BigDecimal orderAmount;

    @ApiModelProperty(value = "净收入（元）")
    private BigDecimal netIncome;

    @ApiModelProperty(value = "总商品数（个）")
    private Integer goodsNum;

    @ApiModelProperty(value = "商品分类（个）")
    private Integer goodsTypeNum;

}
