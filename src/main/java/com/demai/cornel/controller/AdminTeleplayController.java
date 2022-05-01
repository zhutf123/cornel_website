/**
 * Copyright (c) 2015 Qunar.com. All Rights Reserved.
 */
package com.demai.cornel.controller;

import com.demai.cornel.dmEnum.ResponseStatusEnum;
import com.demai.cornel.model.Channel;
import com.demai.cornel.model.Teleplay;
import com.demai.cornel.reqParam.ChannelAddParam;
import com.demai.cornel.reqParam.OperateChannelParam;
import com.demai.cornel.reqParam.OperateTeleplayParam;
import com.demai.cornel.service.TeleplayService;
import com.demai.cornel.vo.JsonResult;
import com.hp.gagawa.java.elements.I;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
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
 * @author tfzhu
 */
@Controller @RequestMapping("/admin") @Slf4j public class AdminTeleplayController {

    @Resource private TeleplayService teleplayService;

    /**
     * 查询剧集list
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

    /**
     * 查询剧集list
     * @return
     */
    @RequestMapping(value = "/addTeleplay.json", method = RequestMethod.POST, produces = "application/json; charset=utf-8") @ResponseBody
    public JsonResult addTeleplay(
            @RequestBody OperateTeleplayParam param, HttpServletResponse response) {
        try {
            List<Teleplay> teleplayList = teleplayService.getTeleplayList(param);
            return JsonResult.success(teleplayList);
        } catch (Exception e) {
            log.error("用户登录异常！", e);
        }
        return JsonResult.successStatus(ResponseStatusEnum.NETWORK_ERROR);
    }


    /***
     * 添加/编辑频道
     * @param param
     * @param response
     * @return
     */
    @RequestMapping(value = "/addChannel.json", method = RequestMethod.POST, produces = "application/json; charset=utf-8") @ResponseBody
    public JsonResult addChannel(
            @RequestBody ChannelAddParam param, HttpServletResponse response) {
        try {
            teleplayService.addChannelInfo(param);
            return JsonResult.success("success");
        } catch (DuplicateKeyException e) {
            return JsonResult.error(String.format("%s已存在,不可重复添加", param.getName()));
        } catch (Exception e) {
            log.error("添加频道信息异常！", e);
        }
        return JsonResult.successStatus(ResponseStatusEnum.NETWORK_ERROR);
    }

    /**
     * 查询频道list
     * @return
     */
    @RequestMapping(value = "/channelList.json", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody public JsonResult channelList(
            @RequestBody OperateChannelParam param, HttpServletResponse response) {
        try {
            List<Channel> channelList = teleplayService.getChannelList(param);
            return JsonResult.success(channelList);
        } catch (Exception e) {
            log.error("获取频道list异常！", e);
        }
        return JsonResult.successStatus(ResponseStatusEnum.NETWORK_ERROR);
    }

    /**
     * 查询频道list
     * @return
     */
    @RequestMapping(value = "/getAllOnlineChannel.json", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody public JsonResult getAllOnlineChannel(HttpServletResponse response) {
        try {
            List<Channel> channelList = teleplayService.getAllOnlineChannel();
            return JsonResult.success(channelList);
        } catch (Exception e) {
            log.error("获取频道list异常！", e);
        }
        return JsonResult.successStatus(ResponseStatusEnum.NETWORK_ERROR);
    }

    /***
     * 添加/编辑频道
     * @param param
     * @param response
     * @return
     */
    @RequestMapping(value = "/delChannel.json", method = RequestMethod.GET, produces = "application/json; charset=utf-8") @ResponseBody
    public JsonResult delChannel(@RequestParam Long id, HttpServletResponse response) {
        try {
            teleplayService.delChannelInfo(id);
            return JsonResult.success("success");
        } catch (Exception e) {
            log.error("删除频道异常！", e);
        }
        return JsonResult.successStatus(ResponseStatusEnum.NETWORK_ERROR);
    }

}
