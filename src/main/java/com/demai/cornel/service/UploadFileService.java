package com.demai.cornel.service;

import com.demai.cornel.constant.ConfigProperties;
import com.demai.cornel.util.AliStaticSourceUtil;
import com.demai.cornel.util.IDUtils;
import com.demai.cornel.util.StringUtil;
import com.demai.cornel.util.json.JsonUtil;
import com.demai.cornel.vo.uploadfile.UploadResp;
import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.startup.ContextConfig;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import static com.demai.cornel.config.BannerConfig.downloadUrl;

@Service @Slf4j public class UploadFileService {
    @Resource private ConfigProperties configProperties;
    @Resource private DownloadFileService downloadFileService;

    @PostConstruct private void initStoreFolder() {
        try {
            File f = new File(configProperties.uploadLocation);
            if (f.exists() && f.isDirectory()) {
                return;
            }
            f.setWritable(true, false);
            f.mkdirs();
            log.info("upload文件件初始化创建成功");
        } catch (Exception e) {
            log.error("upload文件件初始化创建失败！异常原因:{}", e);
        }
    }

    public UploadResp uploadFile(MultipartFile files,String name,Integer type) throws IOException {
        try {
            String fileName = files.getOriginalFilename();
            if (StringUtil.isBlank(name)){
                fileName = name;
            }
            log.info("file name is [{}]", fileName);
            String path = configProperties.uploadLocation + IDUtils.getUUID();
            File saveFile = new File(path);
            FileUtils.copyInputStreamToFile(files.getInputStream(), saveFile);
            if (type == 0) {
                UploadResp resp = AliStaticSourceUtil.uploadVideo(fileName,path+".mp4");
                if (resp != null) {
                    String url = AliStaticSourceUtil.getVideoUrl(resp.getSourceId());
                    resp.setUrl(url);
                }
                return resp;
            } else {
                return AliStaticSourceUtil.uploadImg(path+".jpg");
            }
        } catch (Exception e) {
            log.error("save file fail ", e);
            return null;
        } finally {
            if (files != null && files.getInputStream() != null) {
                files.getInputStream().close();
            }
        }
    }

    public static void main(String[] args) {
        String as = "常用工具.png";
        Integer last = as.lastIndexOf(".");
        System.out.println(as.substring(last + 1));
        System.out.println("name" + as.substring(as.lastIndexOf(".")));

    }
}