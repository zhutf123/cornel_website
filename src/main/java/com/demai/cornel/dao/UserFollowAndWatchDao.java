/**
 * Copyright (c) 2015 Qunar.com. All Rights Reserved.
 */
package com.demai.cornel.dao;

import com.demai.cornel.model.UserFollowVideo;
import com.demai.cornel.model.UserWatchVideo;

/**
 * Create By tfzhu  2022/4/30  11:10 AM
 *
 * @author tfzhu
 */
public interface UserFollowAndWatchDao {
    
    void update(UserWatchVideo teleplay);

    void save(UserWatchVideo teleplay);

    void userFollowUpdate(UserFollowVideo teleplay);

    void userFollowSave(UserFollowVideo teleplay);

}
