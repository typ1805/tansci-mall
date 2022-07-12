package com.tansci.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName： UploadVo.java
 * @ClassPath： com.tansci.domain.vo.UploadVo.java
 * @Description： 文件VO
 * @Author： tanyp
 * @Date： 2022/03/29 15:18
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "文件VO")
public class UploadVo {

    @ApiModelProperty(value = "原始文件名称")
    private String oldName;

    @ApiModelProperty(value = "新文件名称")
    private String newName;

    @ApiModelProperty(value = "访问路径")
    private String path;

}
