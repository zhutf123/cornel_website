package com.demai.cornel.service;

import com.demai.cornel.dao.BannerInfoDao;
import com.demai.cornel.holder.UserHolder;
import com.demai.cornel.model.BannerInfo;
import com.demai.cornel.reqParam.OperateBannerInfoParam;
import com.demai.cornel.reqParam.QueryBannerInfoParam;
import com.demai.cornel.util.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author tfzhu
 * @Date: 2020-01-07    22:33
 */
@Slf4j @Service public class BannerInfoService {

    @Resource
    private BannerInfoDao bannerInfoDao;

    /***
     * 添加、编辑广告位
     * @param param
     */
    public void operateBannerInfo(OperateBannerInfoParam param) {
        BannerInfo bannerInfo = BannerInfo.builder().
                id(param.getId())
                .mainImage(param.getMainImage())
                .mainSource(param.getMainSource())
                .videoId(param.getVideoId())
                .videoSource(param.getVideoSource())
                .videoUrl(param.getVideoUrl())
                .title(param.getTitle())
                .depict(param.getDepict())
                .type(param.getType())
                .seq(param.getSeq())
                .status(BannerInfo.BannerInfoStatusEnum.getBannerInfoStatusEnum(param.getStatus(), null) != null ?
                        param.getStatus() :
                        null)
                .operator(Long.parseLong(UserHolder.getValue("uid")))
                .operatorName(UserHolder.getValue("name")).build();
        if (param.getId() != null) {
            bannerInfo.setOperateTime(DateUtils.now());
            bannerInfoDao.update(bannerInfo);
        } else {
            bannerInfoDao.save(bannerInfo);
        }
    }

    public List<BannerInfo> getBannerInfoList(QueryBannerInfoParam param){
        return bannerInfoDao.getBannerInfoList(param);
    }


    public Integer getBannerInfoAllNum(QueryBannerInfoParam param){
        return bannerInfoDao.getBannerInfoAllNum(param);
    }


    public List<BannerInfo> getBannerInfoListForUser(){
        QueryBannerInfoParam  param = QueryBannerInfoParam.builder()
                .status(BannerInfo.BannerInfoStatusEnum.ONLINE.getValue())
                .build();
        return bannerInfoDao.getBannerInfoList(param);
    }

}
