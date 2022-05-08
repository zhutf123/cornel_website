package com.demai.cornel.controller;

import com.demai.cornel.dmEnum.ResponseStatusEnum;
import com.demai.cornel.model.Channel;
import com.demai.cornel.reqParam.QueryChannelParam;
import com.demai.cornel.service.ChannelService;
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
@Controller @RequestMapping("/user") @Slf4j public class UserChannelInfoController {

    @Resource private ChannelService channelService;

    /**
     * 查询频道list
     *
     * @return
     */
    @RequestMapping(value = "/channelList.json", method = RequestMethod.GET, produces = "application/json; charset=utf-8") @ResponseBody
    public JsonResult channelList(HttpServletResponse response) {
        try {
            List<Channel> channelList = channelService.getChannelListForUser();
            return JsonResult.success(channelList);
        } catch (Exception e) {
            log.error("获取频道list异常！", e);
        }
        return JsonResult.successStatus(ResponseStatusEnum.NETWORK_ERROR);
    }

}
