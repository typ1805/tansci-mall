package com.tansci.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName： OrderStatusCountVo.java
 * @ClassPath： com.tansci.domain.vo.OrderStatusCountVo.java
 * @Description： 订单状态统计
 * @Author： tanyp
 * @Date： 2022/8/19 15:04
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "订单状态统计")
public class OrderStatusCountVo {

    @ApiModelProperty(value = "订单数")
    private Integer orderNum;

    @ApiModelProperty(value = "指标")
    private Integer label;

}
