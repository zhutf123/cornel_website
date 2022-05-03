/**
 * Copyright (c) 2019 dm.com. All Rights Reserved.
 */
package com.demai.cornel.dao;

import com.demai.cornel.model.UserPayInfo;
import com.demai.cornel.model.UserRoleInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Create By zhutf 19-10-6 下午1:18
 *
 * @author tfzhu
 */
public interface UserPayInfoDao {
    /**
     * @param userPayInfo
     */
    void update(UserPayInfo userPayInfo);

    /**
     * @param userPayInfo
     */
    void save(UserPayInfo userPayInfo);

    List<UserPayInfo> getUserPayInfoList(@Param("userId") Long userId);

}
