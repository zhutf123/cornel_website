/**
 * Copyright (c) 2015 Qunar.com. All Rights Reserved.
 */
package com.demai.cornel.controller;

import com.demai.cornel.Resp.UserLoginResp;
import com.demai.cornel.constant.ConfigProperties;
import com.demai.cornel.dmEnum.ResponseStatusEnum;
import com.demai.cornel.model.ChannelGroup;
import com.demai.cornel.model.UserInfo;
import com.demai.cornel.reqParam.QueryUserParam;
import com.demai.cornel.reqParam.UserLoginParam;
import com.demai.cornel.service.UserLoginService;
import com.demai.cornel.service.UserService;
import com.demai.cornel.vo.JsonListResult;
import com.demai.cornel.vo.JsonResult;
import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

import static com.demai.cornel.util.CookieAuthUtils.COOKIE_ADMIN_USER;

/**
 * @author tfzhu
 */
@Controller @RequestMapping("/admin") @Slf4j public class AdminUserController {

    @Resource private UserService userService;

    /**
     * @return
     */
    @RequestMapping(value = "/userList.json", method = RequestMethod.POST, produces = "application/json; charset=utf-8") @ResponseBody
    public JsonListResult allUserInfoList(
            @RequestBody QueryUserParam param, HttpServletResponse response) {
        try {
            List<UserInfo> userInfos = userService.getAllUserInfoList(param);
            Integer allNum = userService.getAllUserInfoNum(param);
            return JsonListResult.success(userInfos,allNum);
        } catch (Exception e) {
            log.error("后台获取用户list失败！", e);
        }
        return JsonListResult.successStatus(ResponseStatusEnum.NETWORK_ERROR);
    }

}
