/**
 * Copyright (c) 2015 Qunar.com. All Rights Reserved.
 */
package com.demai.cornel.dao;

import com.demai.cornel.model.BannerInfo;
import com.demai.cornel.model.Channel;
import com.demai.cornel.model.ChannelGroup;
import com.demai.cornel.reqParam.QueryBannerInfoParam;
import com.demai.cornel.reqParam.QueryChannelGroupParam;
import com.demai.cornel.reqParam.QueryChannelParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Create By tfzhu  2022/4/30  11:10 AM
 *
 * @author tfzhu
 */
public interface BannerInfoDao {
    void update(BannerInfo bannerInfo);

    void save(BannerInfo bannerInfo);

    List<BannerInfo> getBannerInfoList(QueryBannerInfoParam param);

    Integer getBannerInfoAllNum(QueryBannerInfoParam param);

}
