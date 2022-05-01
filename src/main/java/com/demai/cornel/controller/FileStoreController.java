package com.demai.cornel.controller;

import com.demai.cornel.service.DownloadFileService;
import com.demai.cornel.service.UploadFileService;
import com.demai.cornel.vo.JsonResult;
import com.demai.cornel.vo.uploadfile.UploadResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @Author tfzhu
 * @Date: 2020-01-17    16:19
 */
@RestController @RequestMapping("/admin/file/") @Slf4j public class FileStoreController {

    @Resource private UploadFileService uploadFileService;
    @Resource private DownloadFileService downloadFileService;

    @PostMapping(value = "/upload", headers = ("content-type=multipart/*")) @ResponseBody public JsonResult upload(
            @RequestParam("file") MultipartFile req, @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "type", required = true) Integer type) {
        log.info("upload the file the file key is [{}] name is:{}", name);
        try {
            UploadResp resp = uploadFileService.uploadFile(req, name, type);
            return JsonResult.success(resp);
        } catch (IOException e) {
            log.error("upload file fail due to ", e);
            return JsonResult.error("upload fail");
        }
        
    }

}
