package com.tansci.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @path：com.tansci.domain.dto.SysUserDto.java
 * @className：SysUserDto.java
 * @description：用户DTO
 * @author：tanyp
 * @dateTime：2022/03/29 10:38
 * @editNote：
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "用户DTO")
public class SysUserDto {

    @ApiModelProperty(value = "ID")
    private String id;

    @ApiModelProperty(value = "用户名称")
    private String username;

    @ApiModelProperty(value = "用户昵称")
    private String nickname;

    @ApiModelProperty(value = "新密码")
    private String password;

    @ApiModelProperty(value = "旧密码")
    private String oldPassword;

}
