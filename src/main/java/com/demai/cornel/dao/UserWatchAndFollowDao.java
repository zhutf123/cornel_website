/**
 * Copyright (c) 2015 Qunar.com. All Rights Reserved.
 */
package com.demai.cornel.dao;

import com.demai.cornel.Resp.UserWatchAndFollowVideoResp;
import com.demai.cornel.model.UserFollowVideo;
import com.demai.cornel.model.UserWatchVideo;
import com.demai.cornel.reqParam.QueryWatchAndFollowVideoParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Create By tfzhu  2022/4/30  11:10 AM
 *
 * @author tfzhu
 */
public interface UserWatchAndFollowDao {
    
    void update(UserWatchVideo teleplay);

    void save(UserWatchVideo teleplay);

    List<UserWatchVideo> getUserWatchVideoList(QueryWatchAndFollowVideoParam param);

    Integer getUserWatchVideoAllNum(QueryWatchAndFollowVideoParam param);

    UserWatchVideo getUserWatchSeqByTeleplayId(@Param("teleplayId")Long teleplayId, @Param("userId")Long userId);

    void userFollowUpdate(UserFollowVideo teleplay);

    void userFollowSave(UserFollowVideo teleplay);

    UserFollowVideo getUserFollowVideoByVid(@Param("vid")Long vid,@Param("userId")Long userId);

    UserWatchVideo getUserWatchVideoByVid(@Param("teleplayId")Long teleplayId,@Param("userId")Long userId);

    List<UserFollowVideo> getUserFollowVideoList(QueryWatchAndFollowVideoParam param);

    Integer getUserFollowVideoAllNum(QueryWatchAndFollowVideoParam param);

}
