package com.demai.cornel.controller;

import com.demai.cornel.dmEnum.ResponseStatusEnum;
import com.demai.cornel.model.BannerInfo;
import com.demai.cornel.reqParam.OperateBannerInfoParam;
import com.demai.cornel.reqParam.QueryBannerInfoParam;
import com.demai.cornel.service.BannerInfoService;
import com.demai.cornel.vo.JsonListResult;
import com.demai.cornel.vo.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Author tfzhu
 * @Date: 2020-01-07    22:19
 */
@Controller @RequestMapping("/user") @Slf4j public class UserBannerInfoController {

    @Resource BannerInfoService bannerService;


    /**
     * 用户侧查询剧集list
     * @return
     */
    @RequestMapping(value = "/bannerList.json", method = RequestMethod.POST, produces = "application/json; charset=utf-8") @ResponseBody
    public JsonResult bannerList(HttpServletResponse response) {
        try {
            List<BannerInfo> bannerList = bannerService.getBannerInfoListForUser();
            return JsonResult.success(bannerList);
        } catch (Exception e) {
            log.error("获取轮播图list异常！", e);
        }
        return JsonResult.successStatus(ResponseStatusEnum.NETWORK_ERROR);
    }

}
