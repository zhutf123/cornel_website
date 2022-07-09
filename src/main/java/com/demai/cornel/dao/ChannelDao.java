/**
 * Copyright (c) 2015 Qunar.com. All Rights Reserved.
 */
package com.demai.cornel.dao;

import com.demai.cornel.model.Channel;
import com.demai.cornel.model.ChannelGroup;
import com.demai.cornel.reqParam.QueryChannelGroupParam;
import com.demai.cornel.reqParam.QueryChannelParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Create By tfzhu  2022/4/30  11:10 AM
 *
 * @author tfzhu
 */
public interface ChannelDao {
    void update(Channel channel);

    void save(Channel channel);

    void updateChannelGroup(ChannelGroup channel);

    void saveChannelGroup(ChannelGroup channel);

    List<Channel> queryChannelList(QueryChannelParam param);
    
    List<Channel> getAllChannelList();

    List<Channel> getChannelListForUser();

    List<Channel> queryChannelByIds(@Param("ids")List<Long> ids);

    Integer queryChannelAllNum(QueryChannelParam param);

    List<Channel> queryAllChannel();

    List<Channel> queryAllOnlineChannel();

    List<Channel> suggestChannel(@Param("name") String name,@Param("type") Integer type);

    List<ChannelGroup> queryChannelGroupList(QueryChannelGroupParam param);

    Integer queryChannelGroupAllNum(QueryChannelGroupParam param);

    void removeChildChannel(@Param("groupId") Long groupId, @Param("channelId") Long channelId);
}
