/**
 * Copyright (c) 2015 Qunar.com. All Rights Reserved.
 */
package com.demai.cornel.controller;

import com.demai.cornel.Resp.UserLoginResp;
import com.demai.cornel.annotation.AccessControl;
import com.demai.cornel.constant.ConfigProperties;
import com.demai.cornel.dmEnum.ResponseStatusEnum;
import com.demai.cornel.holder.UserHolder;
import com.demai.cornel.reqParam.UserAddParam;
import com.demai.cornel.reqParam.UserLoginParam;
import com.demai.cornel.reqParam.UserLoginSendMsgParam;
import com.demai.cornel.reqParam.UserRegisterParam;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Optional;

import static com.demai.cornel.util.CookieAuthUtils.COOKIE_ADMIN_USER;
import static com.demai.cornel.util.CookieAuthUtils.KEY_USER_NAME;
import static com.demai.cornel.util.CookieAuthUtils.c_key;

/**
 * @author tfzhu
 */
@Controller @RequestMapping("/user") @Slf4j public class UserLoginRegisterController {

    @Resource private UserLoginService userLoginService;
    @Resource private UserService userService;
    @Resource private ConfigProperties configProperties;

    /***
     * 给用户手机号 发送短信验证码 需要补充逻辑 在n分钟内，发送x条的限制
     * 60s 内最大发3次
     * @param phone 手机号
     * @return
     */
    @RequestMapping(value = "/sendCode.json", method = RequestMethod.POST) @ResponseBody @AccessControl(value = "60_3") public JsonResult userLoginSendCode(
            @RequestBody UserLoginSendMsgParam phone) {
        try {
            log.debug("send code access [{}]", JacksonUtils.obj2String(phone));
            return JsonResult.successStatus(userLoginService.sendLoginCodeMsg(phone.getPhone()));
        } catch (Exception e) {
            log.error("用户发送短信异常！", e);
            return JsonResult.successStatus(ResponseStatusEnum.NETWORK_ERROR);
        }
    }

    /***
     * 用户注册
     * @param param
     * @return
     */
    @RequestMapping(value = "/register.json", method = RequestMethod.POST) @ResponseBody public JsonResult userRegister(
            @RequestBody UserRegisterParam param, HttpServletResponse response) {
        Preconditions.checkNotNull(param);
        String cKey = userService.userRegister(param);
        setUserCookie(cKey, response);
        return JsonResult.success(cKey);
    }

    private void setUserCookie(String value, HttpServletResponse response) {
        Cookie cookie = new Cookie(COOKIE_ADMIN_USER, value);
        cookie.setMaxAge(24 * 60 * 60);
        cookie.setDomain(configProperties.cookieDomain);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    /**
     * @return
     */
    @RequestMapping(value = "/login.json", method = RequestMethod.POST, produces = "application/json; charset=utf-8") @ResponseBody public JsonResult doUserLogin(
            @RequestBody UserLoginParam param, HttpServletResponse response) {
        try {
            Preconditions.checkNotNull(param);
            Preconditions.checkNotNull(param.getJscode());
            UserLoginResp login = userLoginService.doLogin(param);
            if (login.getCode().compareTo(UserLoginResp.CODE_ENUE.SUCCESS.getValue()) == 0) {
                setUserCookie(String.format(c_key, login.getUserId(), login.getOpenId()),response);
                return JsonResult.success(login);
            } else {
                return JsonResult.successStatus(UserLoginResp.CODE_ENUE.getByValue(login.getCode()));
            }
        } catch (Exception e) {
            log.error("用户登录异常！", e);
        }
        return JsonResult.successStatus(ResponseStatusEnum.NETWORK_ERROR);
    }

    @RequestMapping(value = "/update-user.json", method = RequestMethod.POST, produces = "application/json; charset=utf-8") @ResponseBody public JsonResult addUser(
            @RequestBody UserAddParam param) {
        try {
            Preconditions.checkNotNull(param);
            return JsonResult.success(userService.updateUserInfo(param));
        } catch (Exception e) {
            log.error("用户登录异常！", e);
        }
        return JsonResult.success(ResponseStatusEnum.NETWORK_ERROR);
    }

    @RequestMapping(value = "/get-user.json", method = RequestMethod.POST, produces = "application/json; charset=utf-8") @ResponseBody public JsonResult getUserInfo() {
        try {
            return JsonResult.success(userService.getUserInfoResp());
        } catch (Exception e) {
            log.error("用户登录异常！", e);
        }
        return JsonResult.success(ResponseStatusEnum.NETWORK_ERROR);
    }

    @RequestMapping(value = "/check-user.json", method = RequestMethod.POST, produces = "application/json; charset=utf-8") @ResponseBody public JsonResult checkAdminRoleUser(
            HttpServletRequest request, HttpServletResponse response) {
        try {
            String curUser = Optional.ofNullable(UserHolder.getValue(CookieAuthUtils.KEY_USER_NAME)).orElse(null);
            if (StringUtil.isNotEmpty(curUser)) {
                return JsonResult.success(userService.getUserRoleId(curUser));
            }
            JsonResult.success(Lists.newArrayList());
        } catch (Exception e) {
            log.error("检测用户信息异常！", e);
        }
        return JsonResult.success(ResponseStatusEnum.NETWORK_ERROR);
    }

    @RequestMapping(value = "/setV.json", method = RequestMethod.GET) @ResponseBody public JsonResult setUserCookieInfo(
            @RequestParam(value = "key", required = true) String key, HttpServletResponse response) {
        try {
            log.info("get ckey info {}", key);
            Map<String, String> userInfoMap = CookieAuthUtils.getUserFromCKey(key);
            log.info("get ckey info {}", JsonUtil.toJson(userInfoMap));
            String curUser = null;
            if (MapUtils.isNotEmpty(userInfoMap)) {
                UserHolder.set(userInfoMap);
                curUser = userInfoMap.get(KEY_USER_NAME);
            }
            log.info("get curUser info {}", curUser);

//            if (StringUtil.isNotEmpty(curUser)) {
//                List<String> roleIds = userService.getUserRoleId(curUser);
//                if (!CollectionUtils.isEmpty(roleIds)) {
//                    Cookie cookie = new Cookie(ContextConsts.COOKIE_CKEY_NAME, key);
//                    cookie.setMaxAge(24 * 60 * 60);
//                    cookie.setDomain(configProperties.cookieDomain);
//                    cookie.setPath("/");
//                    response.addCookie(cookie);
//                }
//            }
            JsonResult.success(0);
        } catch (Exception e) {
            log.error("检测用户信息异常！", e);
        }
        return JsonResult.success(ResponseStatusEnum.NETWORK_ERROR);
    }

}
