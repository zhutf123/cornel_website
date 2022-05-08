/**
 * Copyright (c) 2015 Qunar.com. All Rights Reserved.
 */
package com.demai.cornel.service;

import com.demai.cornel.Resp.UserRankInfoResp;
import com.demai.cornel.dao.RankInfoDao;
import com.demai.cornel.dao.TeleplayDao;
import com.demai.cornel.holder.UserHolder;
import com.demai.cornel.model.BannerInfo;
import com.demai.cornel.model.RankInfo;
import com.demai.cornel.model.RankInfoExt;
import com.demai.cornel.reqParam.OperateRankInfoExtParam;
import com.demai.cornel.reqParam.OperateRankInfoParam;
import com.demai.cornel.reqParam.QueryRankInfoParam;
import com.demai.cornel.util.DateUtils;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
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
        List<RankInfo> rankInfos =  rankInfoDao.getRankInfoList(param);
        if (CollectionUtils.isNotEmpty(rankInfos)) {
            rankInfos.stream().forEach(r ->{
                List<RankInfoExt> teleplayList = rankInfoDao.getRankInfoExtList(r.getId());
                StringBuilder sb = new StringBuilder();
                teleplayList.stream().forEach(t ->{
                    sb.append(t.getTeleplayName()).append(",");
                });
                r.setTeleplayNames(sb.toString());
            });
        }
        return rankInfos;
    }

    public List<UserRankInfoResp> getRankInfoListForUser(){
        List<RankInfo> rankInfoList =  rankInfoDao.getRankInfoListForUser();
        List<UserRankInfoResp> resp = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(rankInfoList)) {
            rankInfoList.stream().forEach(r ->{
                UserRankInfoResp userRankInfoResp = UserRankInfoResp.builder().build();
                BeanUtils.copyProperties(r,userRankInfoResp);
                List<UserRankInfoResp.UserTeleplayResp> userTeleplayRespList = Lists.newArrayList();
                List<RankInfoExt> teleplayList = rankInfoDao.getRankInfoExtInfo(r.getId(),0,3);
                teleplayList.stream().forEach(t ->{
                    UserRankInfoResp.UserTeleplayResp us = UserRankInfoResp.UserTeleplayResp.builder().build();
                    BeanUtils.copyProperties(t,us);
                    us.setTitle(t.getTeleplayName());
                    us.setTip("更新至第x集");
                    userTeleplayRespList.add(us);
                });

                userRankInfoResp.setTeleplayList(userTeleplayRespList);
                resp.add(userRankInfoResp);
            });
        }
        return resp;
    }


    public Integer getRankInfoAllNum(QueryRankInfoParam param){
        return rankInfoDao.getRankInfoAllNum(param);
    }

    /***
     * 新增、编辑排行版剧集
     * @param param
     */
    public void operateRankInfoVideo(OperateRankInfoExtParam param){
        RankInfoExt bannerInfo = RankInfoExt.builder().
                id(param.getId())
                .weight(param.getWeight())
                .status(RankInfoExt.BannerInfoExtStatusEnum.getBannerInfoExtStatusEnum(param.getStatus(), null) != null ?
                        param.getStatus() :
                        null)
                .teleplayId(param.getTeleplayId())
                .rankInfoId(param.getRankInfoId())
                .operator(Long.parseLong(UserHolder.getValue("uid")))
                .operatorName(UserHolder.getValue("name")).build();
        if (param.getId() != null) {
            bannerInfo.setOperateTime(DateUtils.now());
            rankInfoDao.updateRankInfoExt(bannerInfo);
        } else {
            rankInfoDao.saveRankInfoExt(bannerInfo);
        }
    }


    /***
     * 获取排行榜剧集
     * @param rankInfoId
     */
    public List<RankInfoExt> getRankInfoVideoById(Long rankInfoId) {
        return rankInfoDao.getRankInfoExtList(rankInfoId);
    }

}
