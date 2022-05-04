package com.demai.cornel.dao;

import com.demai.cornel.model.CommentInfo;
import com.demai.cornel.reqParam.QueryCommentInfoParam;

import java.util.List;

public interface CommentInfoDao {
    
    void update(CommentInfo commentInfo);

    void save(CommentInfo commentInfo);

    List<CommentInfo> getCommentInfoList(QueryCommentInfoParam param);

    Integer getCommentInfoAllNum(QueryCommentInfoParam param);


}
