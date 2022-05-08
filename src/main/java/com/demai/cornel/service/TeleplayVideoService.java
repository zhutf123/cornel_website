/**
 * Copyright (c) 2015 Qunar.com. All Rights Reserved.
 */
package com.demai.cornel.service;

import com.demai.cornel.Resp.UserVideoInfoResp;
import com.demai.cornel.dao.ChannelDao;
import com.demai.cornel.dao.TeleplayDao;
import com.demai.cornel.dao.TeleplayVideoDao;
import com.demai.cornel.holder.UserHolder;
import com.demai.cornel.model.Channel;
import com.demai.cornel.model.Teleplay;
import com.demai.cornel.model.TeleplayVideo;
import com.demai.cornel.reqParam.OperateChannelParam;
import com.demai.cornel.reqParam.OperateTeleplayParam;
import com.demai.cornel.reqParam.OperateTeleplayVideoParam;
import com.demai.cornel.reqParam.QueryChannelParam;
import com.demai.cornel.reqParam.QueryTeleplayParam;
import com.demai.cornel.reqParam.QueryTeleplayVideoParam;
import com.demai.cornel.util.BeanMapper;
import com.demai.cornel.util.DateUtils;
import com.google.common.collect.Lists;
import jodd.bean.BeanUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
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
@Service @Slf4j public class TeleplayVideoService {

    @Resource private TeleplayVideoDao teleplayVideoDao;
    @Resource private TeleplayVideoDao teleplayVideoDao;

    public UserVideoInfoResp queryTeleplayVideoById(Long vid) {
        UserVideoInfoResp resp = UserVideoInfoResp.builder().build();
        TeleplayVideo video = teleplayVideoDao.queryTeleplayVideoById(vid);
        List<TeleplayVideo> teleplayList = teleplayVideoDao.queryTeleplayVideoByTeleplayIds(Lists.newArrayList(video.getTeleplayId()));
        List<UserVideoInfoResp.UserTeleplayVideoResp> videoList = Lists.newArrayList();
        teleplayList.stream().forEach(t ->{
            UserVideoInfoResp.UserTeleplayVideoResp v = UserVideoInfoResp.UserTeleplayVideoResp.builder().build();
            //根据用户身份判断lock状态
            v.setLock(Boolean.FALSE);
            BeanUtils.copyProperties(t,v);
            videoList.add(v);
        });
        videoList.sort((a, b) -> a.getSeq().compareTo(b.getSeq()));
        BeanUtils.copyProperties(video,resp);
        resp.setVideoList(videoList);
        return resp;
    }


    public List<TeleplayVideo> getTeleplayVideoList(QueryTeleplayVideoParam param) {
        return teleplayVideoDao.queryTeleplayVideoList(param);
    }

    public Integer getTeleplayVideoAllNum(QueryTeleplayVideoParam param) {
        return teleplayVideoDao.queryTeleplayVideoAllNum(param);
    }


    public void operateTeleplayVideo(OperateTeleplayVideoParam param) throws DuplicateKeyException {
        try {
            TeleplayVideo teleplayVideo = TeleplayVideo.builder()
                    .id(param.getId())
                    .title(param.getTitle())
                    .mainImage(param.getMainImage())
                    .mainSource(param.getMainSource())
                    .videoSource(param.getVideoSource())
                    .videoUrl(param.getVideoUrl())
                    .videoTime(param.getVideoTime())
                    .vip(param.getVip())
                    .seq(param.getSeq())
                    .teleplayId(param.getTeleplayId())
                    .recommend(param.getRecommend())
                    .status(TeleplayVideo.TeleplayVideoStatusEnum.getTeleplayVideoStatusEnum(param.getStatus(), null)
                            != null ? param.getStatus() : null)
                    
                    .operator(Long.parseLong(UserHolder.getValue("uid")))
                    .operatorName(UserHolder.getValue("name"))
                    .build();
            if (param.getId() != null) {
                if (param.getStatus() == TeleplayVideo.TeleplayVideoStatusEnum.ONLINE.getValue()) {
                    teleplayVideo.setOperateTime(DateUtils.now());
                }
                teleplayVideoDao.update(teleplayVideo);
            } else {
                teleplayVideoDao.save(teleplayVideo);
            }
        } catch (DuplicateKeyException e) {
            throw e;
        } catch (Exception e) {
            log.error("保存频道信息错误", e);
        }
    }

}
