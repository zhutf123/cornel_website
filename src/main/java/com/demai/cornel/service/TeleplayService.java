/**
 * Copyright (c) 2015 Qunar.com. All Rights Reserved.
 */
package com.demai.cornel.service;

import com.demai.cornel.dao.ChannelDao;
import com.demai.cornel.dao.TeleplayDao;
import com.demai.cornel.holder.UserHolder;
import com.demai.cornel.model.Channel;
import com.demai.cornel.model.Teleplay;
import com.demai.cornel.reqParam.ChannelAddParam;
import com.demai.cornel.reqParam.OperateChannelParam;
import com.demai.cornel.reqParam.OperateTeleplayParam;
import com.demai.cornel.vo.WeChat.WechatCode2SessionResp;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Create By tfzhu  2022/5/1  3:16 PM
 *
 * @author tfzhu
 */
@Service @Slf4j public class TeleplayService {

    @Resource private TeleplayDao teleplayDao;
    @Resource private ChannelDao channelDao;

    public List<Teleplay> getTeleplayList(OperateTeleplayParam param) {
        try {
            List<Teleplay> teleplayList = teleplayDao.queryTeleplayList(param);
            if (CollectionUtils.isNotEmpty(teleplayList)) {
                teleplayList.forEach(t -> {
                    t.setStatusDesc(Teleplay.TeleplayStatusEnum
                            .getTeleplayStatusEnum(t.getStatus(), Teleplay.TeleplayStatusEnum.ERROR_CODE).getExpr());
                    t.setChannelDesc(Lists.newArrayList(""));
                });
                return teleplayList;
            }
        } catch (Exception e) {
            log.error("查询剧集list异常", e);
        }
        return Lists.newArrayList();
    }

    /***
     * 获取频道list
     * @param param
     * @return
     */
    public List<Channel> getChannelList(OperateChannelParam param) {
        try {
            List<Channel> channelList = channelDao.queryChannelList(param);
            if (CollectionUtils.isNotEmpty(channelList)) {
                channelList.forEach(t -> {
                    t.setStatusDesc(Teleplay.TeleplayStatusEnum
                            .getTeleplayStatusEnum(t.getStatus(), Teleplay.TeleplayStatusEnum.ERROR_CODE).getExpr());
                });
                return channelList;
            }
        } catch (Exception e) {
            log.error("查询频道list异常", e);
        }
        return Lists.newArrayList();
    }

    public void addChannelInfo(ChannelAddParam param) throws DuplicateKeyException {
        try {
            Channel channel;
            channel = Channel.builder()
                    .name(param.getName())
                    .weight(param.getWeight())
                    .type(param.getType())
                    .status(param.getStatus())
                    .operator(Long.parseLong(UserHolder.getValue("uid")))
                    .operatorName(UserHolder.getValue("name"))
                    .build();
            channelDao.save(channel);
        } catch (DuplicateKeyException e) {
            throw e;
        } catch (Exception e) {
            log.error("保存频道信息错误", e);
        }
    }

}
