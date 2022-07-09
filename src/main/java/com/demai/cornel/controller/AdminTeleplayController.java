/**
 * Copyright (c) 2015 Qunar.com. All Rights Reserved.
 */
package com.demai.cornel.controller;

import com.demai.cornel.Resp.SuggestTeleplayResp;
import com.demai.cornel.dmEnum.ResponseStatusEnum;
import com.demai.cornel.model.Channel;
import com.demai.cornel.model.ChannelGroup;
import com.demai.cornel.model.Teleplay;
import com.demai.cornel.model.TeleplayVideo;
import com.demai.cornel.model.TeleplayVideoBrowseData;
import com.demai.cornel.reqParam.OperateChannelGroupParam;
import com.demai.cornel.reqParam.OperateChannelParam;
import com.demai.cornel.reqParam.OperateTeleplayParam;
import com.demai.cornel.reqParam.OperateTeleplayVideoParam;
import com.demai.cornel.reqParam.QueryChannelGroupParam;
import com.demai.cornel.reqParam.QueryChannelParam;
import com.demai.cornel.reqParam.QueryTeleplayParam;
import com.demai.cornel.reqParam.QueryTeleplayVideoBrowseDataParam;
import com.demai.cornel.reqParam.QueryTeleplayVideoParam;
import com.demai.cornel.service.ChannelService;
import com.demai.cornel.service.TeleplayService;
import com.demai.cornel.service.TeleplayVideoBrowseDataService;
import com.demai.cornel.service.TeleplayVideoService;
import com.demai.cornel.vo.JsonListResult;
import com.demai.cornel.vo.JsonResult;
import com.google.common.base.Preconditions;
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
    @Resource private TeleplayVideoService teleplayVideoService;
    @Resource private TeleplayVideoBrowseDataService teleplayVideoBrowseDataService;
    @Resource private ChannelService channelService;

    /**
     * 查询剧集list
     * @return
     */
    @RequestMapping(value = "/teleplayList.json", method = RequestMethod.POST, produces = "application/json; charset=utf-8") @ResponseBody
    public JsonListResult teleplayList(
            @RequestBody QueryTeleplayParam param, HttpServletResponse response) {
        try {
            List<Teleplay> teleplayList = teleplayService.getTeleplayList(param);
            Integer allNum = teleplayService.getTeleplayAllNum(param);
            return JsonListResult.success(teleplayList,allNum);
        } catch (Exception e) {
            log.error("获取剧集list异常！", e);
        }
        return JsonListResult.successStatus(ResponseStatusEnum.NETWORK_ERROR);
    }

    /**
     * 查询剧集list
     *
     * @return
     */
    @RequestMapping(value = "/teleplayExport.json", method = RequestMethod.POST, produces = "application/json; charset=utf-8") @ResponseBody public JsonListResult teleplayExport(
            @RequestBody QueryTeleplayParam param, HttpServletResponse response) {
        try {
            List<Teleplay> teleplayList = teleplayService.getTeleplayList(param);
            Integer allNum = teleplayService.getTeleplayAllNum(param);
            return JsonListResult.success(teleplayList, allNum);
        } catch (Exception e) {
            log.error("获取剧集list异常！", e);
        }
        return JsonListResult.successStatus(ResponseStatusEnum.NETWORK_ERROR);
    }



    /**
     * 查询剧集list
     * @return
     */
    @RequestMapping(value = "/getTeleplayInfoById.json", method = RequestMethod.GET) @ResponseBody
    public JsonListResult getTeleplayInfoById(
            @RequestParam("id")Long id, HttpServletResponse response) {
        try {
            Teleplay teleplay = teleplayService.getTeleplayInfoById(id);
            return JsonListResult.success(teleplay);
        } catch (Exception e) {
            log.error("获取剧集list异常！", e);
        }
        return JsonListResult.successStatus(ResponseStatusEnum.NETWORK_ERROR);
    }

    /**
     * 保存、编辑剧集
     * @return
     */
    @RequestMapping(value = "/operateTeleplay.json", method = RequestMethod.POST, produces = "application/json; charset=utf-8") @ResponseBody
    public JsonResult operateTeleplay(
            @RequestBody OperateTeleplayParam param, HttpServletResponse response) {
        try {
            teleplayService.operateTeleplay(param);
            return JsonResult.success("success");
        } catch (Exception e) {
            log.error("添加、修改剧集信息异常！", e);
        }
        return JsonResult.successStatus(ResponseStatusEnum.NETWORK_ERROR);
    }

    /**
     * 查询频道list
     * @return
     */
    @RequestMapping(value = "/suggestTeleplay.json", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody public JsonResult suggestTeleplay(@RequestParam("name")String name,@RequestParam(value = "type", required = false)Integer type, HttpServletResponse response) {
        try {
            Preconditions.checkNotNull(name);
            List<SuggestTeleplayResp> channelList = teleplayService.suggestTeleplay(name);
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
    @RequestMapping(value = "/operateChannel.json", method = RequestMethod.POST, produces = "application/json; charset=utf-8") @ResponseBody
    public JsonResult operateChannel(
            @RequestBody OperateChannelParam param, HttpServletResponse response) {
        try {
            channelService.operateChannel(param);
            return JsonResult.success("success");
        } catch (DuplicateKeyException e) {
            return JsonResult.error(String.format("%s已存在,不可重复添加", param.getName()));
        } catch (Exception e) {
            log.error("添加、修改频道信息异常！", e);
        }
        return JsonResult.successStatus(ResponseStatusEnum.NETWORK_ERROR);
    }

    /**
     * 查询频道list
     * @return
     */
    @RequestMapping(value = "/channelList.json", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody public JsonListResult channelList(
            @RequestBody QueryChannelParam param, HttpServletResponse response) {
        try {
            List<Channel> channelList = channelService.getChannelList(param);
            Integer allNum = channelService.getChannelAllNum(param);
            return JsonListResult.success(channelList,allNum);
        } catch (Exception e) {
            log.error("获取频道list异常！", e);
        }
        return JsonListResult.successStatus(ResponseStatusEnum.NETWORK_ERROR);
    }



    /**
     * 查询频道list
     * @return
     */
    @RequestMapping(value = "/allChannel.json", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody public JsonListResult allChannelList(HttpServletResponse response) {
        try {
            List<Channel> channelList = channelService.getAllChannelList();
            return JsonListResult.success(channelList);
        } catch (Exception e) {
            log.error("获取频道list异常！", e);
        }
        return JsonListResult.successStatus(ResponseStatusEnum.NETWORK_ERROR);
    }

    /**
     * 查询频道list
     * @return
     */
    @RequestMapping(value = "/suggestChannel.json", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody public JsonResult suggestChannel(@RequestParam("name")String name,@RequestParam(value = "type", required = false)Integer type, HttpServletResponse response) {
        try {
            Preconditions.checkNotNull(name);
            List<Channel> channelList = channelService.suggestChannel(name, type);
            return JsonResult.success(channelList);
        } catch (Exception e) {
            log.error("获取频道list异常！", e);
        }
        return JsonResult.successStatus(ResponseStatusEnum.NETWORK_ERROR);
    }

    /***
     * 添加/编辑频道
     * @param id
     * @param response
     * @return
     */
    @RequestMapping(value = "/delChannel.json", method = RequestMethod.GET, produces = "application/json; charset=utf-8") @ResponseBody
    public JsonResult delChannel(@RequestParam Long id, HttpServletResponse response) {
        try {
            Preconditions.checkNotNull(id);
            channelService.delChannelInfo(id);
            return JsonResult.success("success");
        } catch (Exception e) {
            log.error("删除频道异常！", e);
        }
        return JsonResult.successStatus(ResponseStatusEnum.NETWORK_ERROR);
    }

    /**
     * 查询剧集list
     * @return
     */
    @RequestMapping(value = "/teleplayVideoList.json", method = RequestMethod.POST, produces = "application/json; charset=utf-8") @ResponseBody
    public JsonListResult teleplayVideoList(
            @RequestBody QueryTeleplayVideoParam param, HttpServletResponse response) {
        try {
            Preconditions.checkNotNull(param.getTeleplayId());
            List<TeleplayVideo> teleplayVideoList = teleplayVideoService.getTeleplayVideoList(param);
            Integer allNum = teleplayVideoService.getTeleplayVideoAllNum(param);
            return JsonListResult.success(teleplayVideoList, allNum);
        } catch (Exception e) {
            log.error("获取子剧集list！", e);
        }
        return JsonListResult.successStatus(ResponseStatusEnum.NETWORK_ERROR);
    }

    /***
     * 添加/编辑子剧集
     * @param param
     * @param response
     * @return
     */
    @RequestMapping(value = "/operateTeleplayVideo.json", method = RequestMethod.POST, produces = "application/json; charset=utf-8") @ResponseBody public JsonResult operateTeleplayVideo(
            @RequestBody OperateTeleplayVideoParam param, HttpServletResponse response) {
        try {
            Preconditions.checkNotNull(param);
            teleplayVideoService.operateTeleplayVideo(param);
            return JsonResult.success("success");
        }  catch (Exception e) {
            log.error("添加频道信息异常！", e);
        }
        return JsonResult.successStatus(ResponseStatusEnum.NETWORK_ERROR);
    }

    /***
     * 添加/编辑子剧集
     * @param param
     * @param response
     * @return
     */
    @RequestMapping(value = "/teleplayVideoDetail.json", method = RequestMethod.POST, produces = "application/json; charset=utf-8") @ResponseBody public JsonListResult teleplayVideoDetail(
            @RequestBody QueryTeleplayVideoBrowseDataParam param, HttpServletResponse response) {
        try {
            Preconditions.checkNotNull(param.getVideoId());
            List<TeleplayVideoBrowseData> teleplayVideoBrowseDataList = teleplayVideoBrowseDataService
                    .getTeleplayVideoBrowseDataList(param);
            Integer allNum = teleplayVideoBrowseDataService.getTeleplayVideoBrowseDataAllNum(param);
            return JsonListResult.success(teleplayVideoBrowseDataList, allNum);
        } catch (Exception e) {
            log.error("子剧集详情数据异常！", e);
        }
        return JsonListResult.successStatus(ResponseStatusEnum.NETWORK_ERROR);
    }

    /**
     * 查询聚合频道list
     * @return
     */
    @RequestMapping(value = "/channelGroupList.json", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody public JsonListResult channelGroupList(
            @RequestBody QueryChannelGroupParam param, HttpServletResponse response) {
        try {
            List<ChannelGroup> channelList = channelService.getChannelGroupList(param);
            Integer allNum = channelService.getChannelGroupAllNum(param);
            return JsonListResult.success(channelList,allNum);
        } catch (Exception e) {
            log.error("获取频道list异常！", e);
        }
        return JsonListResult.successStatus(ResponseStatusEnum.NETWORK_ERROR);
    }

    /**
     * 查询聚合标签添加、编辑
     *
     * @return
     */
    @RequestMapping(value = "/operateChannelGroup.json", method = RequestMethod.POST, produces = "application/json; charset=utf-8") @ResponseBody public JsonResult operateChannelGroup(
            @RequestBody OperateChannelGroupParam param, HttpServletResponse response) {
        try {
            Preconditions.checkNotNull(param);
            channelService.operateChannelGroup(param);
            return JsonResult.success("success");
        } catch (Exception e) {
            log.error("添加频道信息异常！", e);
        }
        return JsonResult.successStatus(ResponseStatusEnum.NETWORK_ERROR);
    }


    /***
     * 添加/编辑频道
     * @param id
     * @param response
     * @return
     */
    @RequestMapping(value = "/delChannelGroup.json", method = RequestMethod.GET, produces = "application/json; charset=utf-8") @ResponseBody
    public JsonResult delChannelGroup(@RequestParam Long id, HttpServletResponse response) {
        try {
            Preconditions.checkNotNull(id);
            channelService.delChannelGroupInfo(id);
            return JsonResult.success("success");
        } catch (Exception e) {
            log.error("删除聚合标签异常！", e);
        }
        return JsonResult.successStatus(ResponseStatusEnum.NETWORK_ERROR);
    }

    /***
     * 添加/编辑频道
     * @param id
     * @param response
     * @return
     */
    @RequestMapping(value = "/removeChildChannel.json", method = RequestMethod.GET, produces = "application/json; charset=utf-8") @ResponseBody public JsonResult removeChildChannel(
            @RequestParam Long groupId,@RequestParam Long channelId, HttpServletResponse response) {
        try {
            Preconditions.checkNotNull(groupId);
            Preconditions.checkNotNull(channelId);
            channelService.removeChildChannel(groupId,channelId);
            return JsonResult.success("success");
        } catch (Exception e) {
            log.error("删除聚合标签异常！", e);
        }
        return JsonResult.successStatus(ResponseStatusEnum.NETWORK_ERROR);
    }


}
