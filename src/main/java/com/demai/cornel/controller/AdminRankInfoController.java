package com.demai.cornel.controller;

import com.demai.cornel.dmEnum.ResponseStatusEnum;
import com.demai.cornel.model.BannerInfo;
import com.demai.cornel.model.RankInfo;
import com.demai.cornel.reqParam.OperateBannerInfoParam;
import com.demai.cornel.reqParam.OperateRankInfoExtParam;
import com.demai.cornel.reqParam.OperateRankInfoParam;
import com.demai.cornel.reqParam.QueryBannerInfoParam;
import com.demai.cornel.reqParam.QueryRankInfoParam;
import com.demai.cornel.service.BannerInfoService;
import com.demai.cornel.service.RankInfoService;
import com.demai.cornel.vo.JsonListResult;
import com.demai.cornel.vo.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
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
@Controller @RequestMapping("/admin") @Slf4j public class AdminRankInfoController {

    @Resource RankInfoService rankInfoService;


    /**
     * 保存、编辑排行榜
     * @return
     */
    @RequestMapping(value = "/operateRankInfo.json", method = RequestMethod.POST, produces = "application/json; charset=utf-8") @ResponseBody
    public JsonResult operateRankInfo(
            @RequestBody OperateRankInfoParam param, HttpServletResponse response) {
        try {
            rankInfoService.operateRankInfo(param);
            return JsonResult.success("success");
        } catch (Exception e) {
            log.error("添加、修改剧集信息异常！", e);
        }
        return JsonResult.successStatus(ResponseStatusEnum.NETWORK_ERROR);
    }


    /**
     * 查询排行榜list
     * @return
     */
    @RequestMapping(value = "/rankInfoList.json", method = RequestMethod.POST, produces = "application/json; charset=utf-8") @ResponseBody
    public JsonListResult rankInfoList(
            @RequestBody QueryRankInfoParam param, HttpServletResponse response) {
        try {
            List<RankInfo> bannerList = rankInfoService.getRankInfoList(param);
            Integer allNum = rankInfoService.getRankInfoAllNum(param);
            return JsonListResult.success(bannerList,allNum);
        } catch (Exception e) {
            log.error("获取排行榜list异常！", e);
        }
        return JsonListResult.successStatus(ResponseStatusEnum.NETWORK_ERROR);
    }


    /**
     * 保存、编辑排行榜
     * @return
     */
    @RequestMapping(value = "/operateRankInfoVideo.json", method = RequestMethod.POST, produces = "application/json; charset=utf-8") @ResponseBody
    public JsonResult operateRankInfoVideo(
            @RequestBody OperateRankInfoExtParam param, HttpServletResponse response) {
        try {
            rankInfoService.operateRankInfoVideo(param);
            return JsonResult.success("success");
        } catch (Exception e) {
            log.error("添加、修改剧集信息异常！", e);
        }
        return JsonResult.successStatus(ResponseStatusEnum.NETWORK_ERROR);
    }

    /**
     * 保存、编辑排行榜
     * @return
     */
    @RequestMapping(value = "/getRankInfoVideo.json", method = RequestMethod.GET, produces = "application/json; charset=utf-8") @ResponseBody
    public JsonResult getRankInfoVideo(
            @RequestParam Long rankInfoId, HttpServletResponse response) {
        try {
            rankInfoService.getRankInfoVideoById(rankInfoId);
            return JsonResult.success("success");
        } catch (Exception e) {
            log.error("添加、修改剧集信息异常！", e);
        }
        return JsonResult.successStatus(ResponseStatusEnum.NETWORK_ERROR);
    }


}
