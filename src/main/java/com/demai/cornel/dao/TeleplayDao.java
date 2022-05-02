/**
 * Copyright (c) 2015 Qunar.com. All Rights Reserved.
 */
package com.demai.cornel.dao;

import com.demai.cornel.model.Teleplay;
import com.demai.cornel.reqParam.QueryTeleplayParam;

import java.util.List;

/**
 * Create By tfzhu  2022/4/30  11:10 AM
 *
 * @author tfzhu
 */
public interface TeleplayDao {
    void update(Teleplay teleplay);

    void save(Teleplay teleplay);

    List<Teleplay> queryTeleplayList(QueryTeleplayParam param);

    Integer queryTeleplayAllNum(QueryTeleplayParam param);

}
