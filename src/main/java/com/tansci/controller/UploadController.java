package com.tansci.controller;

import com.tansci.common.WrapMapper;
import com.tansci.common.Wrapper;
import com.tansci.domain.vo.UploadVo;
import com.tansci.service.UploadService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName： UploadController.java
 * @ClassPath： com.tansci.controller.UploadController.java
 * @Description： 上传文件
 * @Author： tanyp
 * @Date： 2022/03/29 15:18
 **/
@Slf4j
@RestController
@RequestMapping("/upload")
@Api(value = "upload", tags = "上传文件")
public class UploadController {

    @Autowired
    private UploadService uploadService;

    /**
     * @MonthName： uploadImage
     * @Description： 上传图片
     * @Author： tanyp
     * @Date： 2022/03/29 15:18
     * @Param： [file]
     * @return： com.tanersci.common.Wrapper<com.tansci.domain.vo.UploadVo>
     **/
    @PostMapping("/uploadImage")
    public Wrapper<UploadVo> uploadImage(@RequestParam("file") MultipartFile file) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, uploadService.uploadImage(file));
    }

    /**
     * @MonthName： delFile
     * @Description： 删除文件
     * @Author： tanyp
     * @Date： 2022/03/29 15:18
     * @Param： [fileName]
     * @return： java.lang.Boolean
     **/
    @GetMapping("/delFile")
    public Wrapper<Boolean> delFile(String fileName) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, uploadService.delFile(fileName));
    }

    /**
     * @MonthName： downloadFile
     * @Description： 下载文件
     * @Author： tanyp
     * @Date： 2022/03/29 15:18
     * @Param： [fileName, downloadPath]
     * @return： java.lang.Boolean
     **/
    @GetMapping("/downloadFile")
    public Wrapper<Boolean> downloadFile(String fileName, String downloadPath) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, uploadService.downloadFile(fileName, downloadPath));
    }

}
