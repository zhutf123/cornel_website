package com.demai.cornel.controller;

import com.demai.cornel.dmEnum.ResponseStatusEnum;
import com.demai.cornel.model.BannerInfo;
import com.demai.cornel.model.Teleplay;
import com.demai.cornel.reqParam.OperateBannerInfoParam;
import com.demai.cornel.reqParam.OperateTeleplayParam;
import com.demai.cornel.reqParam.QueryBannerInfoParam;
import com.demai.cornel.reqParam.QueryTeleplayParam;
import com.demai.cornel.service.BannerInfoService;
import com.demai.cornel.vo.JsonListResult;
import com.demai.cornel.vo.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @Author tfzhu
 * @Date: 2020-01-07    22:19
 */
@Controller @RequestMapping("/admin") @Slf4j public class AdminBannerInfoController {

    @Resource BannerInfoService bannerService;


    /**
     * 保存、编辑广告位
     * @return
     */
    @RequestMapping(value = "/operateBannerInfo.json", method = RequestMethod.POST, produces = "application/json; charset=utf-8") @ResponseBody
    public JsonResult operateBannerInfo(
            @RequestBody OperateBannerInfoParam param, HttpServletResponse response) {
        try {
            bannerService.operateBannerInfo(param);
            return JsonResult.success("success");
        } catch (Exception e) {
            log.error("添加、修改剧集信息异常！", e);
        }
        return JsonResult.successStatus(ResponseStatusEnum.NETWORK_ERROR);
    }


    /**
     * 查询剧集list
     * @return
     */
    @RequestMapping(value = "/bannerList.json", method = RequestMethod.POST, produces = "application/json; charset=utf-8") @ResponseBody
    public JsonListResult bannerList(
            @RequestBody QueryBannerInfoParam param, HttpServletResponse response) {
        try {
            List<BannerInfo> bannerList = bannerService.getBannerInfoList(param);
            Integer allNum = bannerService.getBannerInfoAllNum(param);
            return JsonListResult.success(bannerList,allNum);
        } catch (Exception e) {
            log.error("获取轮播图list异常！", e);
        }
        return JsonListResult.successStatus(ResponseStatusEnum.NETWORK_ERROR);
    }

}
