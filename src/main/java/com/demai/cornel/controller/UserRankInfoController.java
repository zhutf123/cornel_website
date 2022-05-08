package com.demai.cornel.controller;

import com.demai.cornel.Resp.UserRankInfoResp;
import com.demai.cornel.dmEnum.ResponseStatusEnum;
import com.demai.cornel.model.BannerInfo;
import com.demai.cornel.model.RankInfo;
import com.demai.cornel.reqParam.OperateCommentInfoParam;
import com.demai.cornel.reqParam.UserChangeRankInfoParam;
import com.demai.cornel.service.BannerInfoService;
import com.demai.cornel.service.RankInfoService;
import com.demai.cornel.vo.JsonResult;
import lombok.extern.slf4j.Slf4j;
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
@Controller @RequestMapping("/user") @Slf4j public class UserRankInfoController {

    @Resource RankInfoService rankInfoService;

    /**
     * 用户侧查询剧集list
     *
     * @return
     */
    @RequestMapping(value = "/rankInfoList.json", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody public JsonResult rankInfoList(
            HttpServletResponse response) {
        try {
            List<UserRankInfoResp> rankInfoRespList = rankInfoService.getRankInfoListForUser();
            return JsonResult.success(rankInfoRespList);
        } catch (Exception e) {
            log.error("小剧场获取数据异常！", e);
        }
        return JsonResult.successStatus(ResponseStatusEnum.NETWORK_ERROR);
    }


    /**
     * 用户侧查询剧集list
     *
     * @return
     */
    @RequestMapping(value = "/changeRankInfo.json", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody public JsonResult changeRankInfo(
            @RequestParam UserChangeRankInfoParam param, HttpServletResponse response) {
        try {
            List<UserRankInfoResp.UserTeleplayResp> rankInfoRespList = rankInfoService.changeRankInfo(param);
            return JsonResult.success(rankInfoRespList);
        } catch (Exception e) {
            log.error("小剧场获取数据异常！", e);
        }
        return JsonResult.successStatus(ResponseStatusEnum.NETWORK_ERROR);
    }

}
