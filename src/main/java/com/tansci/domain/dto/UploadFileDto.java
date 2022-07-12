package com.tansci.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.InputStream;

/**
 * @ClassName： UploadFileDto.java
 * @ClassPath： com.tansci.domain.dto.UploadFileDto.java
 * @Description： 上传文件
 * @Author： tanyp
 * @Date： 2022/03/29 10:38
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "上传文件Dto")
public class UploadFileDto {

    @ApiModelProperty(value = " ftp 服务器ip地址")
    private String host;

    @ApiModelProperty(value = " ftp 服务器port，默认是21")
    private Integer port;

    @ApiModelProperty(value = " ftp 服务器用户名")
    private String account;

    @ApiModelProperty(value = " ftp 服务器密码")
    private String passwd;

    @ApiModelProperty(value = " ftp 服务器存储图片的绝对路径")
    private String workingDir;

    @ApiModelProperty(value = "上传到ftp 服务器文件名")
    private String fileName;

    @ApiModelProperty(value = " 文件流")
    private InputStream inputStream;

    @ApiModelProperty(value = " 下载文件的路径")
    private String downloadPath;

}
