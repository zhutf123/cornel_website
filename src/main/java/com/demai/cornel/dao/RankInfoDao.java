/**
 * Copyright (c) 2015 Qunar.com. All Rights Reserved.
 */
package com.demai.cornel.dao;

import com.demai.cornel.model.AclInfo;
import com.demai.cornel.model.RankInfo;

/**
 * Create By tfzhu  2022/5/6  5:31 AM
 *
 * @author tfzhu
 */
public interface RankInfoDao {

    public void update(RankInfo aclInfo);

    public void save(RankInfo aclInfo);
}
