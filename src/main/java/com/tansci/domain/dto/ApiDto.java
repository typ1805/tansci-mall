package com.tansci.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @path：com.tansci.domain.dto.ApiDto.java
 * @className：ApiDto.java
 * @description：ApiDto
 * @author：tanyp
 * @dateTime：2022/3/29 21:06 
 * @editNote：
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "ApiDto")
public class ApiDto {

    @ApiModelProperty(value = "商品id")
    private String goodsId;

    @ApiModelProperty(value = "用户id")
    private String userId;

    @ApiModelProperty(value = "付款码类型")
    private Integer type;

}
