package com.tansci.domain.vo;

import com.tansci.domain.Shop;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @ClassName： SysUserVo.java
 * @ClassPath： com.tansci.domain.vo.SysUserVo.java
 * @Description： 用户
 * @Author： tanyp
 * @Date： 2022/03/29 16:14
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "用户Dto")
public class SysUserVo {

    @ApiModelProperty(value = "用户名称")
    private String username;

    @ApiModelProperty(value = "用户昵称")
    private String nickname;

    @ApiModelProperty(value = "用户类型：1、管理员，2、普通用户")
    private Integer type;

    @ApiModelProperty(value = "会员等级")
    private Integer member;

    @ApiModelProperty(value = "积分")
    private Integer integral;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "凭证")
    private String token;

    @ApiModelProperty(value = "登录时间")
    private LocalDateTime loginTime;

    @ApiModelProperty(value = "店铺信息")
    private Shop shop;

}
