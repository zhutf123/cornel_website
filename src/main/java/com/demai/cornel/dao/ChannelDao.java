/**
 * Copyright (c) 2015 Qunar.com. All Rights Reserved.
 */
package com.demai.cornel.dao;

import com.demai.cornel.model.Channel;
import com.demai.cornel.model.Teleplay;
import com.demai.cornel.reqParam.OperateChannelParam;
import com.demai.cornel.reqParam.OperateTeleplayParam;

import java.util.List;

/**
 * Create By tfzhu  2022/4/30  11:10 AM
 *
 * @author tfzhu
 */
public interface ChannelDao {
    void update(Channel channel);

    void save(Channel channel);

    List<Channel> queryChannelList(OperateChannelParam param);

    List<Channel> queryAllChannel();

    List<Channel> getAllOnlineChannel();

}
