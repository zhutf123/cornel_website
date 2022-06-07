/**
 * Copyright (c) 2015 Qunar.com. All Rights Reserved.
 */
package com.demai.cornel.controller;

import com.demai.cornel.Resp.UserInfoCenterResp;
import com.demai.cornel.dmEnum.ResponseStatusEnum;
import com.demai.cornel.model.Teleplay;
import com.demai.cornel.model.UserInfo;
import com.demai.cornel.reqParam.UserQueryTeleplayParam;
import com.demai.cornel.service.UserService;
import com.demai.cornel.vo.JsonListResult;
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
 * Create By tfzhu  2022/6/8  04:34
 *
 * @author tfzhu
 */
@Controller
@Slf4j
@RequestMapping("/user")
public class UserInfoController {

    @Resource
    private UserService userService;
    
    @RequestMapping(value = "/myInfo.json", method = RequestMethod.POST, produces = "application/json; charset=utf-8") @ResponseBody
    public JsonListResult teleplayList(@RequestBody UserQueryTeleplayParam param, HttpServletResponse response) {
        try {
            UserInfoCenterResp userInfo = userService.userCenterInfo();
            if (userInfo!=null){
                return JsonListResult.success(userInfo);
            }
        } catch (Exception e) {
            log.error("获取我的信息失败！", e);
        }
        return JsonListResult.successStatus(ResponseStatusEnum.NETWORK_ERROR);
    }

}
