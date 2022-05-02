/**
 * Copyright (c) 2015 Qunar.com. All Rights Reserved.
 */
package com.demai.cornel.dao;

import com.demai.cornel.model.TeleplayVideoBrowseData;
import com.demai.cornel.reqParam.QueryTeleplayVideoBrowseDataParam;

import java.util.List;

/**
 * Create By tfzhu  2022/4/30  11:10 AM
 *
 * @author tfzhu
 */
public interface TeleplayVideoBrowseDataDao {
    void update(TeleplayVideoBrowseData teleplayVideoBrowseData);

    void save(TeleplayVideoBrowseData teleplayVideoBrowseData);

    List<TeleplayVideoBrowseData> getTeleplayVideoBrowseDataList(QueryTeleplayVideoBrowseDataParam param);

    Integer getTeleplayVideoBrowseDataAllNum(QueryTeleplayVideoBrowseDataParam param);


}
