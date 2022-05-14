package com.demai.cornel.controller;

import com.demai.cornel.Resp.UserWatchAndFollowVideoResp;
import com.demai.cornel.dmEnum.ResponseStatusEnum;
import com.demai.cornel.holder.UserHolder;
import com.demai.cornel.reqParam.QueryWatchAndFollowVideoParam;
import com.demai.cornel.service.TeleplayVideoService;
import com.demai.cornel.service.UserWatchAndFollowService;
import com.demai.cornel.vo.JsonListResult;
import com.demai.cornel.vo.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
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
@Controller @RequestMapping("/user") @Slf4j public class UserWatchVideoInfoController {

    @Resource UserWatchAndFollowService userWatchAndFollowService;


    /**
     * 我的观看
     * @return
     */
    @RequestMapping(value = "/myWatch.json", method = RequestMethod.POST, produces = "application/json; charset=utf-8") @ResponseBody
    public JsonListResult myWatch(
            @RequestBody QueryWatchAndFollowVideoParam param,
            HttpServletResponse response) {
        try {
            String uid = UserHolder.getValue("uid");
            if (StringUtils.isBlank(uid)){
                return JsonListResult.successStatus(ResponseStatusEnum.NETWORK_ERROR);
            }
            param.setUserId(Long.parseLong(uid));
            List<UserWatchAndFollowVideoResp> watchList = userWatchAndFollowService.getUserWatchVideoList(param);
            Integer watchNum = userWatchAndFollowService.getUserWatchVideoAllNum(param);
            return JsonListResult.success(watchList, watchNum);
        } catch (Exception e) {
            log.error("最近播放记录异常", e);
        }
        return JsonListResult.successStatus(ResponseStatusEnum.NETWORK_ERROR);
    }

    /**
     * 我的追剧
     *
     * @return
     */
    @RequestMapping(value = "/myFollow.json", method = RequestMethod.POST, produces = "application/json; charset=utf-8") @ResponseBody public JsonResult myFollow(
            HttpServletResponse response) {
        try {
            return JsonResult.success("resp");
        } catch (Exception e) {
            log.error("我的追剧异常", e);
        }
        return JsonResult.successStatus(ResponseStatusEnum.NETWORK_ERROR);
    }

    /**
     * 加入追剧
     * @return
     */
    @RequestMapping(value = "/followVideo.json", method = RequestMethod.GET)
    public JsonResult followVideo(
            @RequestParam Long vid,
            HttpServletResponse response) {
        try {
            String uid = UserHolder.getValue("uid");
            if (StringUtils.isBlank(uid)){
                return JsonResult.successStatus(ResponseStatusEnum.NETWORK_ERROR);
            }
            
            userWatchAndFollowService.followVideoInfo(vid,Long.parseLong(uid));
            return JsonResult.success("success");
        } catch (Exception e) {
            log.error("用户点击某一剧集进入播放详情异常！", e);
        }
        return JsonResult.successStatus(ResponseStatusEnum.NETWORK_ERROR);
    }

}
