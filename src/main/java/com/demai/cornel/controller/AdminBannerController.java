package com.demai.cornel.controller;

import com.demai.cornel.dmEnum.ResponseStatusEnum;
import com.demai.cornel.reqParam.OperateBannerInfoParam;
import com.demai.cornel.reqParam.OperateTeleplayParam;
import com.demai.cornel.service.BannerInfoService;
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

/**
 * @Author tfzhu
 * @Date: 2020-01-07    22:19
 */
@Controller @RequestMapping("/admin") @Slf4j public class AdminBannerController {

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

}
