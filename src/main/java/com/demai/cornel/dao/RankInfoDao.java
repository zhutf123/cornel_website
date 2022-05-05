/**
 * Copyright (c) 2015 Qunar.com. All Rights Reserved.
 */
package com.demai.cornel.dao;

import com.demai.cornel.model.AclInfo;
import com.demai.cornel.model.RankInfo;
import com.demai.cornel.model.RankInfoExt;
import com.demai.cornel.reqParam.QueryRankInfoParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Create By tfzhu  2022/5/6  5:31 AM
 *
 * @author tfzhu
 */
public interface RankInfoDao {

    public void update(RankInfo aclInfo);

    public void save(RankInfo aclInfo);

    public void updateRankInfoExt(RankInfoExt aclInfo);

    public void saveRankInfoExt(RankInfoExt aclInfo);

    List<RankInfo> getRankInfoList(QueryRankInfoParam param);

    Integer getRankInfoAllNum(QueryRankInfoParam param);

    List<RankInfoExt> getRankInfoExtList(@Param("rankInfoId") Long rankInfoId);
}
