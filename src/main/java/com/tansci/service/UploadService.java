package com.tansci.service;

import com.tansci.domain.vo.UploadVo;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName： UploadService.java
 * @ClassPath： com.tansci.service.UploadService.java
 * @Description：上传文件
 * @Author： tanyp
 * @Date： 2022/03/29 15:18
 **/
public interface UploadService {

    /**
     * @MonthName： uploadImage
     * @Description： 上传图片
     * @Author： tanyp
     * @Date： 2022/03/29 15:18
     * @Param： [file]
     * @return： com.tansci.domain.vo.UploadVo
     **/
    UploadVo uploadImage(MultipartFile file);

    /**
     * @MonthName： delFile
     * @Description： 删除文件
     * @Author： tanyp
     * @Date： 2022/03/29 15:18
     * @Param： [fileName]
     * @return： java.lang.Boolean
     **/
    Boolean delFile(String fileName);

    /**
     * @MonthName： downloadFile
     * @Description： 下载文件
     * @Author： tanyp
     * @Date： 2022/03/29 15:18
     * @Param： [fileName, downloadPath]
     * @return： java.lang.Boolean
     **/
    Boolean downloadFile(String fileName, String downloadPath);
}
