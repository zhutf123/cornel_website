/**
 * Copyright (c) 2015 Qunar.com. All Rights Reserved.
 */
package com.demai.cornel.service;

import com.demai.cornel.Resp.SuggestTeleplayResp;
import com.demai.cornel.dao.ChannelDao;
import com.demai.cornel.dao.TeleplayDao;
import com.demai.cornel.dao.TeleplayVideoDao;
import com.demai.cornel.holder.UserHolder;
import com.demai.cornel.model.Channel;
import com.demai.cornel.model.Teleplay;
import com.demai.cornel.model.TeleplayVideo;
import com.demai.cornel.reqParam.OperateTeleplayParam;
import com.demai.cornel.reqParam.QueryTeleplayParam;
import com.demai.cornel.reqParam.UserQueryTeleplayParam;
import com.demai.cornel.util.DateUtils;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;
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
    @Resource private TeleplayVideoDao teleplayVideoDao;

    /***
     * 添加、编辑剧集
     * @param param
     * @throws DuplicateKeyException
     */
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

    /**
     * suggest出剧集，并且包含子剧集，用户用户选择
     * @param name
     * @return
     */
    public List<SuggestTeleplayResp> suggestTeleplay(String name) {
        List<SuggestTeleplayResp> resp = Lists.newArrayList();
        List<Teleplay> teleplayList = teleplayDao.suggestTeleplay(name);
        if (CollectionUtils.isNotEmpty(teleplayList)) {
            List<TeleplayVideo> videoList = teleplayVideoDao.queryTeleplayVideoByTeleplayIds(
                    teleplayList.stream().map(Teleplay::getId).collect(Collectors.toList()));
            ArrayListValuedHashMap<Long, TeleplayVideo> videos = new ArrayListValuedHashMap<Long, TeleplayVideo>();
            if (CollectionUtils.isNotEmpty(videoList)) {
                videoList.stream().forEach(v -> {
                    videos.put(v.getTeleplayId(), v);
                });
            }
            teleplayList.stream().forEach(t -> {
                List<TeleplayVideo> vs = videos.get(t.getId());
                if (CollectionUtils.isNotEmpty(vs)) {
                    vs.sort((a, b) -> a.getSeq().compareTo(b.getSeq()));
                    resp.add(SuggestTeleplayResp.builder()
                            .depict(t.getDepict())
                            .mainSource(t.getMainSource())
                            .mainImage(t.getMainImage())
                            .title(t.getTitle())
                            .videoList(vs)
                            .build());
                }
            });
        }

        return resp;
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

    public Integer getTeleplayAllNum(QueryTeleplayParam param) {
        try {
            return teleplayDao.queryTeleplayAllNum(param);
        } catch (Exception e) {
            log.error("查询剧集list异常", e);
        }
        return 0;
    }

    /***
     * 根据剧集标签后去剧集list
     * @param param
     * @return
     */
    public List<Teleplay> getTeleplayListByChannelId(UserQueryTeleplayParam param) {
        try {
            List<Teleplay> teleplayList = teleplayDao.queryTeleplayListByChannelId(param);
            return teleplayList;
        } catch (Exception e) {
            log.error("查询剧集list异常", e);
        }
        return Lists.newArrayList();
    }

    /***
     * 根据剧集标签后去剧集list
     * @param param
     * @return
     */
    public Integer getTeleplayAllNumByChannelId(UserQueryTeleplayParam param) {
        return teleplayDao.getTeleplayAllNumByChannelId(param);
    }

}
