/**
 * Copyright (c) 2015 Qunar.com. All Rights Reserved.
 */
package com.demai.cornel.service;

import com.demai.cornel.dao.ChannelDao;
import com.demai.cornel.dao.TeleplayDao;
import com.demai.cornel.holder.UserHolder;
import com.demai.cornel.model.Channel;
import com.demai.cornel.model.ChannelGroup;
import com.demai.cornel.model.Teleplay;
import com.demai.cornel.reqParam.OperateChannelGroupParam;
import com.demai.cornel.reqParam.OperateChannelParam;
import com.demai.cornel.reqParam.OperateTeleplayParam;
import com.demai.cornel.reqParam.QueryChannelGroupParam;
import com.demai.cornel.reqParam.QueryChannelParam;
import com.demai.cornel.reqParam.QueryTeleplayParam;
import com.demai.cornel.util.DateUtils;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Create By tfzhu  2022/5/1  3:16 PM
 *
 * @author tfzhu
 */
@Service @Slf4j public class ChannelService {

    @Resource private ChannelDao channelDao;
    
    /***
     * 获取频道list
     * @param param
     * @return
     */
    public List<Channel> getChannelList(QueryChannelParam param) {
        try {
            List<Channel> channelList = channelDao.queryChannelList(param);
            if (CollectionUtils.isNotEmpty(channelList)) {
                return channelList;
            }
        } catch (Exception e) {
            log.error("查询频道list异常", e);
        }
        return Lists.newArrayList();
    }

    /***
     * 获取频道list
     * @param param
     * @return
     */
    public Integer getChannelAllNum(QueryChannelParam param) {
        try {
            return  channelDao.queryChannelAllNum(param);
        } catch (Exception e) {
            log.error("查询频道list异常", e);
        }
        return 0;
    }

    public void operateChannel(OperateChannelParam param) throws DuplicateKeyException {
        try {
            Channel channel = Channel.builder()
                    .id(param.getId())
                    .name(param.getName())
                    .weight(param.getWeight())
                    .type(param.getType())
                    .status(Channel.ChannelStatusEnum.getChannelStatusEnum(param.getStatus(), null) != null ?
                            param.getStatus() :
                            null)
                    .operator(Long.parseLong(UserHolder.getValue("uid")))
                    .operatorName(UserHolder.getValue("name"))
                    .build();
            if (param.getId() != null) {
                channel.setOperateTime(DateUtils.now());
                channelDao.update(channel);
            } else {
                channelDao.save(channel);
            }
        } catch (DuplicateKeyException e) {
            throw e;
        } 
    }

    /**
     * 删除频道信息
     * @param id
     */
    public void delChannelInfo(Long id) {
        Channel channel = Channel.builder().id(id).status(Channel.ChannelStatusEnum.DELETE.getValue())
                .operator(Long.parseLong(UserHolder.getValue("uid"))).operatorName(UserHolder.getValue("name")).build();
        channelDao.update(channel);
    }

    public List<Channel> suggestChannel(String name,Integer type) {
        try {
            List<Channel> channelList = channelDao.suggestChannel(name, type);
            if (CollectionUtils.isNotEmpty(channelList)) {
                return channelList;
            }
        } catch (Exception e) {
            log.error("查询频道list异常", e);
        }
        return Lists.newArrayList();
    }


    /***
     * 获取聚合频道list
     * @param param
     * @return
     */
    public List<ChannelGroup> getChannelGroupList(QueryChannelGroupParam param) {
        try {
            List<ChannelGroup> channelGroupList = channelDao.queryChannelGroupList(param);
            if (CollectionUtils.isNotEmpty(channelGroupList)) {
                channelGroupList.stream().forEach(channelGroup -> {
                    if (CollectionUtils.isNotEmpty(channelGroup.getChannel())) {
                        List<Long> ids = channelGroup.getChannel().stream().map(t -> Long.parseLong(t))
                                .collect(Collectors.toList());
                        List<Channel> channelList = channelDao.queryChannelByIds(ids);
                        channelGroup.setChannelList(channelList);
                        channelGroup.setChannelSize(channelGroup.getChannel().size());
                    }
                });
                return channelGroupList;
            }
        } catch (Exception e) {
            log.error("查询频道list异常", e);
        }
        return Lists.newArrayList();
    }

    /***
     * 获取聚合频道list
     * @param param
     * @return
     */
    public Integer getChannelGroupAllNum(QueryChannelGroupParam param) {
        try {
            return channelDao.queryChannelGroupAllNum(param);
        } catch (Exception e) {
            log.error("查询频道list异常", e);
        }
        return 0;
    }

    /***
     * 获取聚合频道list
     * @param param
     * @return
     */
    public void operateChannelGroup(OperateChannelGroupParam param) {
        ChannelGroup channelGroup = ChannelGroup.builder().
                id(param.getId())
                .name(param.getName())
                .recommend(param.getRecommend())
                .channel(param.getChannel())
                .status(Channel.ChannelStatusEnum.getChannelStatusEnum(param.getStatus(), null) != null ?
                        param.getStatus() :
                        null)
                .operator(Long.parseLong(UserHolder.getValue("uid")))
                .operatorName(UserHolder.getValue("name")).build();
        if (param.getId() != null) {
            channelGroup.setOperateTime(DateUtils.now());
            channelDao.updateChannelGroup(channelGroup);
        } else {
            channelDao.saveChannelGroup(channelGroup);
        }
    }

}
