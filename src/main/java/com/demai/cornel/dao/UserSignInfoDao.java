/**
 * Copyright (c) 2015 Qunar.com. All Rights Reserved.
 */
package com.demai.cornel.dao;

import com.demai.cornel.model.TeleplayVideo;
import com.demai.cornel.model.UserSignInInfo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Create By tfzhu  2022/4/30  3:51 PM
 *
 * @author tfzhu
 */
public interface UserSignInfoDao {
    int update(UserSignInInfo userInfo);

    void save(UserSignInInfo userInfo);

    List<UserSignInInfo> querySignInfoList(@Param("uid")Long id);

    UserSignInInfo queryUserSignInfo(@Param("uid")Long id,@Param("current") String current);

}
