package com.demai.cornel.controller;

import com.demai.cornel.service.BannerService;
import com.demai.cornel.vo.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author tfzhu
 * @Date: 2020-01-07    22:19
 */
@Controller @RequestMapping("/banner") @Slf4j public class BannerController {

    @Resource BannerService bannerService;

    @RequestMapping(value = "/list.json", method = RequestMethod.POST) @ResponseBody public JsonResult getBannerList() {
        return JsonResult.success(bannerService.getBannerDownloadUrl());
    }

    @RequestMapping(value = "/download.json", method = RequestMethod.GET) @ResponseBody public void download(
            @RequestParam(value = "key", required = true) String key, HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        log.info("download the file the key={}", key);
        String fileName = key;
        if (fileName == null || fileName.trim().length() == 0) {
            resp.reset();
            resp.setContentType("text/plain;charset=utf-8");
            PrintWriter writer = resp.getWriter();
            writer.write("error:can't get the file name! 不能获取文件名");
            writer.flush();
            return;
        }
        fileName = "banner/" + fileName;
        bannerService.downloadClassResourceService(fileName, req, resp);
    }

}
