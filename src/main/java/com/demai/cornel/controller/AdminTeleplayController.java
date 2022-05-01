/**
 * Copyright (c) 2015 Qunar.com. All Rights Reserved.
 */
package com.demai.cornel.controller;

import com.demai.cornel.dmEnum.ResponseStatusEnum;
import com.demai.cornel.model.Teleplay;
import com.demai.cornel.reqParam.OperateTeleplayParam;
import com.demai.cornel.service.TeleplayService;
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
 * @author tfzhu
 */
@Controller @RequestMapping("/admin") @Slf4j public class AdminTeleplayController {

    @Resource private TeleplayService teleplayService;

    /**
     * @return
     */
    @RequestMapping(value = "/teleplayList.json", method = RequestMethod.POST, produces = "application/json; charset=utf-8") @ResponseBody public JsonResult teleplayList(
            @RequestBody OperateTeleplayParam param, HttpServletResponse response) {
        try {
            List<Teleplay> teleplayList = teleplayService.getTeleplayList(param);
            return JsonResult.success(teleplayList);
        } catch (Exception e) {
            log.error("用户登录异常！", e);
        }
        return JsonResult.successStatus(ResponseStatusEnum.NETWORK_ERROR);
    }

}
