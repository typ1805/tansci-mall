package com.tansci.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName： SysUserMenu.java
 * @ClassPath： com.tansci.domain.SysUserMenu.java
 * @Description： 用户菜单
 * @Author： tanyp
 * @Date： 2021/7/20 16:25
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName(value = "sys_user_menu")
@ApiModel(value = "用户菜单")
public class SysUserMenu {

    @ApiModelProperty(value = "用户id")
    private String userId;

    @ApiModelProperty(value = "菜单id")
    private Integer menuId;

}
