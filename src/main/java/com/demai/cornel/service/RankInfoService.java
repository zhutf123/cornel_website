/**
 * Copyright (c) 2015 Qunar.com. All Rights Reserved.
 */
package com.demai.cornel.service;

import com.demai.cornel.dao.RankInfoDao;
import com.demai.cornel.holder.UserHolder;
import com.demai.cornel.model.BannerInfo;
import com.demai.cornel.model.RankInfo;
import com.demai.cornel.reqParam.OperateBannerInfoParam;
import com.demai.cornel.reqParam.OperateRankInfoParam;
import com.demai.cornel.reqParam.QueryRankInfoParam;
import com.demai.cornel.util.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Create By tfzhu  2022/5/1  3:16 PM
 *
 * @author tfzhu
 */
@Service @Slf4j public class RankInfoService {

    @Resource private RankInfoDao rankInfoDao;


    /***
     * 添加、编辑排行榜
     * @param param
     */
    public void operateRankInfo(OperateRankInfoParam param) {
        RankInfo bannerInfo = RankInfo.builder().
                id(param.getId())
                .teleplayId(param.getTeleplayId())
                .name(param.getName())
                .weight(param.getWeight())
                .status(BannerInfo.BannerInfoStatusEnum.getBannerInfoStatusEnum(param.getStatus(), null) != null ?
                        param.getStatus() :
                        null)
                .operator(Long.parseLong(UserHolder.getValue("uid")))
                .operatorName(UserHolder.getValue("name")).build();
        if (param.getId() != null) {
            bannerInfo.setOperateTime(DateUtils.now());
            rankInfoDao.update(bannerInfo);
        } else {
            rankInfoDao.save(bannerInfo);
        }
    }


    public List<RankInfo> getRankInfoList(QueryRankInfoParam param){
        return null;
    }

    public Integer getRankInfoAllNum(QueryRankInfoParam param){
        return null;
    }


}
