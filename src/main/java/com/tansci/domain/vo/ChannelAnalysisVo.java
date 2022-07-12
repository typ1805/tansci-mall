package com.tansci.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @ClassName： ChannelAnalysisVo.java
 * @ClassPath： com.tansci.domain.vo.ChannelAnalysisVo.java
 * @Description： 支付渠道总览
 * @Author： tanyp
 * @Date： 2022/4/2 9:33
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "支付渠道总览")
public class ChannelAnalysisVo {

    @ApiModelProperty(value = "指标名称")
    private String name;

    @ApiModelProperty(value = " 指标值")
    private BigDecimal value;

}
