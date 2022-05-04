/**
 * Copyright (c) 2015 Qunar.com. All Rights Reserved.
 */
package com.demai.cornel.service;

import com.demai.cornel.dao.CommentInfoDao;
import com.demai.cornel.holder.UserHolder;
import com.demai.cornel.model.BannerInfo;
import com.demai.cornel.model.Channel;
import com.demai.cornel.model.CommentInfo;
import com.demai.cornel.reqParam.OperateChannelParam;
import com.demai.cornel.reqParam.OperateCommentInfoParam;
import com.demai.cornel.reqParam.QueryBannerInfoParam;
import com.demai.cornel.reqParam.QueryCommentInfoParam;
import com.demai.cornel.util.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Create By tfzhu  2022/5/4  5:24 PM
 *
 * @author tfzhu
 */
@Slf4j @Service
public class CommentInfoService {

    @Resource
    private CommentInfoDao commentInfoDao;

    public void operateCommentInfo(OperateCommentInfoParam param) throws DuplicateKeyException {
        try {
            
        } catch (DuplicateKeyException e) {
            throw e;
        }
    }

    public List<CommentInfo> getCommentInfoList(QueryCommentInfoParam param){
        return commentInfoDao.getCommentInfoList(param);
    }


    public Integer getCommentInfoAllNum(QueryCommentInfoParam param){
        return commentInfoDao.getCommentInfoAllNum(param);
    }
}
