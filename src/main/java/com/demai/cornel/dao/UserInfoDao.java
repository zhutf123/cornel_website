/**
 * Copyright (c) 2015 Qunar.com. All Rights Reserved.
 */
package com.demai.cornel.dao;

import com.demai.cornel.model.Teleplay;
import com.demai.cornel.model.UserInfo;
import org.apache.ibatis.annotations.Param;

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

    UserInfo getUserInfoNoDriverByPhone(@Param("phone") String phone);

    int updateUserOpenIdByUid(@Param("openId") String openId, @Param("userId") Long userId);
}
