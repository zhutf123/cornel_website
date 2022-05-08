package com.demai.cornel.controller;

import com.demai.cornel.Resp.UserVideoInfoResp;
import com.demai.cornel.dmEnum.ResponseStatusEnum;
import com.demai.cornel.model.BannerInfo;
import com.demai.cornel.model.TeleplayVideo;
import com.demai.cornel.service.BannerInfoService;
import com.demai.cornel.service.TeleplayVideoService;
import com.demai.cornel.vo.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Author tfzhu
 * @Date: 2020-01-07    22:19
 */
@Controller @RequestMapping("/user") @Slf4j public class UserVideoInfoController {

    @Resource TeleplayVideoService teleplayVideoService;


    /**
     * 用户点击某一剧集进入播放详情
     * @return
     */
    @RequestMapping(value = "/videoInfo.json", method = RequestMethod.GET, produces = "application/json; charset=utf-8") @ResponseBody public JsonResult videoInfo(
            @RequestParam("vid") Long vid, HttpServletResponse response) {
        try {
            UserVideoInfoResp resp = teleplayVideoService.queryTeleplayVideoById(vid);
            return JsonResult.success(resp);
        } catch (Exception e) {
            log.error("用户点击某一剧集进入播放详情异常！", e);
        }
        return JsonResult.successStatus(ResponseStatusEnum.NETWORK_ERROR);
    }

}
