/**
 * Copyright (c) 2015 Qunar.com. All Rights Reserved.
 */
package com.demai.cornel.service;

import com.demai.cornel.Resp.UserWatchAndFollowVideoResp;
import com.demai.cornel.dao.TeleplayDao;
import com.demai.cornel.dao.TeleplayVideoDao;
import com.demai.cornel.dao.UserWatchAndFollowDao;
import com.demai.cornel.model.Teleplay;
import com.demai.cornel.model.TeleplayVideo;
import com.demai.cornel.model.UserFollowVideo;
import com.demai.cornel.model.UserWatchVideo;
import com.demai.cornel.reqParam.QueryTeleplayVideoParam;
import com.demai.cornel.reqParam.QueryWatchAndFollowVideoParam;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Create By tfzhu  2022/5/10  6:52 AM
 *
 * @author tfzhu
 */
@Service @Slf4j public class UserWatchAndFollowService {

    @Resource
    private UserWatchAndFollowDao userWatchAndFollowDao;
    @Resource
    private TeleplayDao teleplayDao;

    public List<UserWatchAndFollowVideoResp> getUserWatchVideoList(QueryWatchAndFollowVideoParam param) {
       List<UserWatchVideo> videoList = userWatchAndFollowDao.getUserWatchVideoList(param);
       List<UserWatchAndFollowVideoResp> result = Lists.newArrayList();
       if (CollectionUtils.isNotEmpty(videoList)){
           videoList.stream().forEach(v -> {
               Teleplay teleplay = teleplayDao.queryTeleplayInfoByVid(v.getVideoId());
               if (teleplay != null) {
                   UserWatchAndFollowVideoResp resp = UserWatchAndFollowVideoResp.builder().videoId(v.getVideoId())
                           .title(teleplay.getTitle())
                           .mainImage(teleplay.getMainImage())
                           .mainSource(teleplay.getMainSource())
                           .build();
                   result.add(resp);
               }
           });
       }
       return result;
    }

    public Integer getUserWatchVideoAllNum(QueryWatchAndFollowVideoParam param) {
        return userWatchAndFollowDao.getUserWatchVideoAllNum(param);
    }

    /***
     * 加入我的追剧
     * @param vid
     */
    public void followVideoInfo(Long vid,Long userId){
        Teleplay teleplayVideo = teleplayDao.queryTeleplayInfoByVid(vid);
        if (teleplayVideo == null) {
            return;
        }
        UserFollowVideo followVideo = UserFollowVideo.builder()
                .userId(userId)
                .teleplayId(teleplayVideo.getId())
                .build();
        
        userWatchAndFollowDao.userFollowSave(followVideo);
    }



    public List<UserWatchAndFollowVideoResp> getUserFollowVideoList(QueryWatchAndFollowVideoParam param) {
        List<UserWatchVideo> videoList = userWatchAndFollowDao.getUserWatchVideoList(param);
        List<UserWatchAndFollowVideoResp> result = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(videoList)){
            videoList.stream().forEach(v -> {
                Teleplay teleplay = teleplayDao.queryTeleplayInfoByVid(v.getVideoId());
                if (teleplay != null) {
                    UserWatchAndFollowVideoResp resp = UserWatchAndFollowVideoResp.builder().videoId(v.getVideoId())
                            .title(teleplay.getTitle())
                            .mainImage(teleplay.getMainImage())
                            .mainSource(teleplay.getMainSource())
                            .build();
                    result.add(resp);
                }
            });
        }
        return result;
    }

    public Integer getUserFollowVideoAllNum(QueryWatchAndFollowVideoParam param) {
        return userWatchAndFollowDao.getUserWatchVideoAllNum(param);
    }
    
}
