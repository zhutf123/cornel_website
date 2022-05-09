package com.demai.cornel.controller;

import com.demai.cornel.Resp.UserVideoInfoResp;
import com.demai.cornel.dmEnum.ResponseStatusEnum;
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

/**
 * @Author tfzhu
 * @Date: 2020-01-07    22:19
 */
@Controller @RequestMapping("/user") @Slf4j public class UserWatchVideoInfoController {

    @Resource TeleplayVideoService teleplayVideoService;


    /**
     * 我的观看
     * @return
     */
    @RequestMapping(value = "/myWatch.json", method = RequestMethod.POST, produces = "application/json; charset=utf-8") @ResponseBody
    public JsonResult myWatch(
            HttpServletResponse response) {
        try {
            return JsonResult.success("resp");
        } catch (Exception e) {
            log.error("用户点击某一剧集进入播放详情异常！", e);
        }
        return JsonResult.successStatus(ResponseStatusEnum.NETWORK_ERROR);
    }


    /**
     * 我的观看
     * @return
     */
    @RequestMapping(value = "/myFollow.json", method = RequestMethod.POST, produces = "application/json; charset=utf-8") @ResponseBody
    public JsonResult myFollow(
            HttpServletResponse response) {
        try {
            return JsonResult.success("resp");
        } catch (Exception e) {
            log.error("用户点击某一剧集进入播放详情异常！", e);
        }
        return JsonResult.successStatus(ResponseStatusEnum.NETWORK_ERROR);
    }

}
