package com.study.controller;

import com.study.common.bean.CommonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@RestController
@RequestMapping("image")
public class ImageController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImageController.class);

    @Value("${examination.image.path:}")
    private String uploadFilePath;

    /**
     * 上传图片
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public CommonResult upload(MultipartFile file) {
        String fileName = UUID.randomUUID().toString() +"_"+ file.getOriginalFilename();
        String filePath = uploadFilePath+fileName;
        File destFile = new File(filePath);
        if (!destFile.exists()){
            destFile.mkdirs();
        }
        try{
            file.transferTo(destFile);
        }catch(Exception ex){
            LOGGER.error("上传图片失败",ex);
            return CommonResult.failed();
        }
        return CommonResult.success(fileName);
    }
}
