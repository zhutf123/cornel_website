/**
 * Copyright (c) 2015 Qunar.com. All Rights Reserved.
 */
package com.demai.cornel.controller;

import com.demai.cornel.Resp.UserLoginResp;
import com.demai.cornel.annotation.AccessControl;
import com.demai.cornel.constant.ConfigProperties;
import com.demai.cornel.constant.ContextConsts;
import com.demai.cornel.dmEnum.ResponseStatusEnum;
import com.demai.cornel.holder.UserHolder;
import com.demai.cornel.reqParam.UserAddParam;
import com.demai.cornel.reqParam.UserLoginParam;
import com.demai.cornel.reqParam.UserLoginSendMsgParam;
import com.demai.cornel.service.UserLoginService;
import com.demai.cornel.service.UserService;
import com.demai.cornel.util.CookieAuthUtils;
import com.demai.cornel.util.JacksonUtils;
import com.demai.cornel.util.StringUtil;
import com.demai.cornel.util.json.JsonUtil;
import com.demai.cornel.vo.JsonResult;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.demai.cornel.util.CookieAuthUtils.COOKIE_ADMIN_USER;
import static com.demai.cornel.util.CookieAuthUtils.KEY_USER_NAME;

/**
 * @author tfzhu
 */
@Controller @RequestMapping("/admin") @Slf4j public class AdminUserLoginController {

    @Resource private UserLoginService userLoginService;
    @Resource private UserService userService;
    @Resource private ConfigProperties configProperties;

    /**
     * @return
     */
    @RequestMapping(value = "/login.json", method = RequestMethod.POST, produces = "application/json; charset=utf-8") @ResponseBody public JsonResult doUserLogin(
            @RequestBody UserLoginParam param, HttpServletResponse response) {
        try {
            Preconditions.checkNotNull(param.getName());
            Preconditions.checkNotNull(param.getPasswd());
            param.setAdmin(Boolean.TRUE);
            UserLoginResp login = userLoginService.doLogin(param);
            if (login.getCode().compareTo(UserLoginResp.CODE_ENUE.SUCCESS.getValue()) == 0) {
                Cookie cookie = new Cookie(COOKIE_ADMIN_USER, login.getUserId());
                cookie.setMaxAge(24 * 60 * 60);
                cookie.setDomain(configProperties.cookieDomain);
                cookie.setPath("/");
                response.addCookie(cookie);
                return JsonResult.success(login);
            } else {
                return JsonResult.successStatus(UserLoginResp.CODE_ENUE.getByValue(login.getCode()));
            }
        } catch (Exception e) {
            log.error("用户登录异常！", e);
        }
        return JsonResult.successStatus(ResponseStatusEnum.NETWORK_ERROR);
    }

}
