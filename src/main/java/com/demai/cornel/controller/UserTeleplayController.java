package com.demai.cornel.controller;

import com.demai.cornel.dmEnum.ResponseStatusEnum;
import com.demai.cornel.model.Channel;
import com.demai.cornel.model.Teleplay;
import com.demai.cornel.reqParam.UserQueryTeleplayParam;
import com.demai.cornel.service.ChannelService;
import com.demai.cornel.service.TeleplayService;
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
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Author tfzhu
 * @Date: 2020-01-07    22:19
 */
@Controller @RequestMapping("/user") @Slf4j public class UserTeleplayController {

    @Resource private TeleplayService teleplayService;

    /**
     * 查询频道list
     *
     * @return
     */
    @RequestMapping(value = "/teleplayList.json", method = RequestMethod.POST, produces = "application/json; charset=utf-8") @ResponseBody
    public JsonListResult teleplayList(@RequestBody UserQueryTeleplayParam param, HttpServletResponse response) {
        try {
            List<Teleplay> channelList = teleplayService.getTeleplayListByChannelId(param);
            Integer allNum = teleplayService.getTeleplayAllNumByChannelId(param);
            return JsonListResult.success(channelList);
        } catch (Exception e) {
            log.error("获取频道list异常！", e);
        }
        return JsonListResult.successStatus(ResponseStatusEnum.NETWORK_ERROR);
    }

}
