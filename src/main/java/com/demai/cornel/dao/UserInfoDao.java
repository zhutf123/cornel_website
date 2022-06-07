/**
 * Copyright (c) 2015 Qunar.com. All Rights Reserved.
 */
package com.demai.cornel.dao;

import com.demai.cornel.model.Teleplay;
import com.demai.cornel.model.UserInfo;
import com.demai.cornel.reqParam.QueryUserParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * Create By tfzhu  2022/4/30  3:51 PM
 *
 * @author tfzhu
 */
public interface UserInfoDao {
    int update(UserInfo userInfo);

    void save(UserInfo userInfo);

    UserInfo getUserInfoByUserId(@Param("userId") String userId);

    UserInfo getUserInfoByOpenId(@Param("openId") String openId);

    UserInfo getUserInfoByNamePasswd(@Param("name") String name, @Param("passwd") String passwd);

    UserInfo getUserInfoNoDriverByPhone(@Param("phone") String phone);

    int updateUserOpenIdByUid(@Param("openId") String openId, @Param("userId") Long userId);

    List<UserInfo> getAllUserInfoList(QueryUserParam param);

    Integer getAllUserInfoNum(QueryUserParam param);

    void addGoldCoin(@Param("userId") Long userId,@Param("goldCoin")Integer goldCoin);
}

