package com.tansci.service.impl;

import com.tansci.config.FtpConfig;
import com.tansci.domain.dto.UploadFileDto;
import com.tansci.domain.vo.UploadVo;
import com.tansci.exception.BusinessException;
import com.tansci.service.UploadService;
import com.tansci.utils.UUIDUtils;
import com.tansci.utils.UploadFileUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * @ClassName： UploadServiceImpl.java
 * @ClassPath： com.tansci.service.impl.UploadServiceImpl.java
 * @Description： 上传文件
 * @Author： tanyp
 * @Date： 2022/03/29 15:18
 **/
@Slf4j
@Service
public class UploadServiceImpl implements UploadService {

    @Autowired
    private FtpConfig ftpConfig;

    /**
     * @MonthName： uploadImage
     * @Description： 上传图片
     * @Author： tanyp
     * @Date： 2022/03/29 15:18
     * @Param： [file]
     * @return： com.tansci.domain.vo.UploadVo
     **/
    @Override
    public UploadVo uploadImage(MultipartFile file) {
        log.info("=======上传图片开始，图片名称：{}", file.getOriginalFilename());
        try {
            // 1. 取原始文件名
            String oldName = file.getOriginalFilename();

            // 2. ftp 服务器的文件名
            String newName = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")) + UUIDUtils.getUUID(10) + oldName.substring(oldName.lastIndexOf("."));

            // 3.上传图片
            Boolean result = UploadFileUtils.upload(
                    UploadFileDto.builder()
                            .host(ftpConfig.host)
                            .port(ftpConfig.post)
                            .account(ftpConfig.username)
                            .passwd(ftpConfig.password)
                            .workingDir(ftpConfig.basePath)
                            .fileName(newName)
                            .inputStream(file.getInputStream())
                            .build()
            );

            // 4.返回结果
            if (!result) {
                throw new BusinessException("上传图片失败！");
            }

            log.info("=======上传图片结束，新图片名称：{}", newName);
            return UploadVo.builder()
                    .oldName(oldName)
                    .newName(newName)
                    .path(ftpConfig.imageBaseUrl + "/" + newName)
                    .build();
        } catch (Exception e) {
            log.error("=======上传图片异常，异常信息：{}", e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @MonthName： delFile
     * @Description： 删除文件
     * @Author： tanyp
     * @Date： 2022/03/29 15:18
     * @Param： [fileName]
     * @return： java.lang.Boolean
     **/
    @Override
    public Boolean delFile(String fileName) {
        if (Objects.isNull(fileName)) {
            throw new BusinessException("文件名称为空，请核实！");
        }
        try {
            Boolean result = UploadFileUtils.delete(
                    UploadFileDto.builder()
                            .host(ftpConfig.host)
                            .port(ftpConfig.post)
                            .account(ftpConfig.username)
                            .passwd(ftpConfig.password)
                            .workingDir(ftpConfig.basePath)
                            .fileName(fileName)
                            .build()
            );
            return result;
        } catch (Exception e) {
            log.error("=======删除文件异常，异常信息：{}", e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @MonthName： downloadFile
     * @Description： 下载文件
     * @Author： tanyp
     * @Date： 2022/03/29 15:18
     * @Param： [fileName, downloadPath]
     * @return： java.lang.Boolean
     **/
    @Override
    public Boolean downloadFile(String fileName, String downloadPath) {
        if (Objects.isNull(fileName) || Objects.isNull(downloadPath)) {
            throw new BusinessException("文件名称或下载路径为空，请核实！");
        }
        try {
            Boolean result = UploadFileUtils.download(
                    UploadFileDto.builder()
                            .host(ftpConfig.host)
                            .port(ftpConfig.post)
                            .account(ftpConfig.username)
                            .passwd(ftpConfig.password)
                            .workingDir(ftpConfig.basePath)
                            .fileName(fileName)
                            .downloadPath(downloadPath)
                            .build()
            );
            return result;
        } catch (Exception e) {
            log.error("=======下载文件异常，异常信息：{}", e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

}
