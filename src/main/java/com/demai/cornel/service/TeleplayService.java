/**
 * Copyright (c) 2015 Qunar.com. All Rights Reserved.
 */
package com.demai.cornel.service;

import com.demai.cornel.dao.ChannelDao;
import com.demai.cornel.dao.TeleplayDao;
import com.demai.cornel.holder.UserHolder;
import com.demai.cornel.model.Channel;
import com.demai.cornel.model.Teleplay;
import com.demai.cornel.reqParam.OperateChannelParam;
import com.demai.cornel.reqParam.QueryChannelParam;
import com.demai.cornel.reqParam.QueryTeleplayParam;
import com.demai.cornel.reqParam.OperateTeleplayParam;
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
@Service @Slf4j public class TeleplayService {

    @Resource private TeleplayDao teleplayDao;
    @Resource private ChannelDao channelDao;

    public void operateTeleplay(OperateTeleplayParam param) throws DuplicateKeyException {
        try {
            Teleplay teleplay = Teleplay.builder()
                    .id(param.getId())
                    .channel(param.getChannel())
                    .title(param.getTitle())
                    .depict(param.getDepict())
                    .nums(param.getNums())
                    .mainImage(param.getMainImage())
                    .mainSource(param.getMainSource())
                    .vip(param.getVip())
                    .recommend(param.getRecommend())
                    .top(param.getTop())
                    .status(Teleplay.TeleplayStatusEnum.getTeleplayStatusEnum(param.getStatus(), null) != null ?
                            param.getStatus() :
                            null)
                    .operator(Long.parseLong(UserHolder.getValue("uid")))
                    .operatorName(UserHolder.getValue("name"))
                    .build();
            if (param.getId() != null) {
                teleplay.setOperateTime(DateUtils.now());
                teleplayDao.update(teleplay);
            } else {
                teleplayDao.save(teleplay);
            }
        } catch (DuplicateKeyException e) {
            throw e;
        } catch (Exception e) {
            log.error("保存频道信息错误", e);
        }
    }

    public List<Teleplay> getTeleplayList(QueryTeleplayParam param) {
        try {
            List<Teleplay> teleplayList = teleplayDao.queryTeleplayList(param);
            if (CollectionUtils.isNotEmpty(teleplayList)) {
                List<Channel> channelList = channelDao.queryAllOnlineChannel();
                Map<Long, String> channelMap = channelList.stream()
                        .collect(Collectors.toMap(Channel::getId, Channel::getName));
                teleplayList.forEach(t -> {
                    if (CollectionUtils.isNotEmpty(t.getChannel())){
                        List<String> channelNames = Lists.newArrayList();
                        t.getChannel().stream().forEach(c ->{
                            channelNames.add(channelMap.get(Long.parseLong(c)));
                        });
                        t.setChannelDesc(channelNames);
                    }
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
        } catch (Exception e) {
            log.error("保存频道信息错误", e);
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

    public List<Channel> suggestChannel(String name) {
        try {
            List<Channel> channelList = channelDao.suggestChannel(name);
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

}
