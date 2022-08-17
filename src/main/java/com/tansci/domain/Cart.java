package com.tansci.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @path：com.tansci.domain.Cart.java
 * @className：Cart.java
 * @description：购物车表
 * @author：tanyp
 * @dateTime：2022/7/21 10:06
 * @editNote：
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "cart")
@ApiModel(value = "购物车")
public class Cart {

    @ApiModelProperty(value = "购物车id")
    @TableId(type = IdType.ASSIGN_UUID)
    private String cartId;

    @ApiModelProperty(value = "商品id")
    private String goodsId;

    @ApiModelProperty(value = "数量")
    private Integer goodsNum;

    @ApiModelProperty(value = "状态：0、正常，1、失效")
    private Integer status;

    @ApiModelProperty(value = "商户id")
    private String userId;

    @ApiModelProperty(value = "店铺ID")
    private String shopId;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @TableField(exist = false)
    @ApiModelProperty(value = "用户名称")
    private String username;

    @TableField(exist = false)
    @ApiModelProperty(value = "店铺名称")
    private String shopName;

    @TableField(exist = false)
    @ApiModelProperty(value = "商品名称")
    private String goodsName;

    @TableField(exist = false)
    @ApiModelProperty(value = "是否选中")
    private Integer isSelect;

    @TableField(exist = false)
    @ApiModelProperty(value = "商品信息")
    private Goods goods;

    @TableField(exist = false)
    @ApiModelProperty(value = "商品集合")
    private List<Goods> goodsList;

    @TableField(exist = false)
    @ApiModelProperty(value = "购物车ID集合")
    private List<String> cartIds;

}
