/**
 * Copyright (c) 2019 dm.com. All Rights Reserved.
 */
package com.demai.cornel.dao;

import com.demai.cornel.auth.model.AclInfo;

/**
 * Create By zhutf 19-10-6 下午1:08
 */
public interface AclInfoDao {
    public void update(AclInfo aclInfo);

    public void save(AclInfo aclInfo);
}
