/**
 * Copyright (c) 2015 Qunar.com. All Rights Reserved.
 */
package com.demai.cornel.service;

import com.demai.cornel.Resp.UserWatchAndFollowVideoResp;
import com.demai.cornel.dao.UserWatchAndFollowDao;
import com.demai.cornel.model.TeleplayVideo;
import com.demai.cornel.model.UserWatchVideo;
import com.demai.cornel.reqParam.QueryTeleplayVideoParam;
import com.demai.cornel.reqParam.QueryWatchAndFollowVideoParam;
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
    private TeleplayService teleplayService;

    public List<UserWatchAndFollowVideoResp> getUserWatchVideoList(QueryWatchAndFollowVideoParam param) {
       List<UserWatchVideo> videoList = userWatchAndFollowDao.getUserWatchVideoList(param);
       if (CollectionUtils.isNotEmpty(videoList)){
           
       }
       
       return null;
    }

    public Integer getUserWatchVideoAllNum(QueryWatchAndFollowVideoParam param) {
        return userWatchAndFollowDao.getUserWatchVideoAllNum(param);
    }
    
}
