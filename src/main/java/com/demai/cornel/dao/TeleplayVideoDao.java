/**
 * Copyright (c) 2015 Qunar.com. All Rights Reserved.
 */
package com.demai.cornel.dao;

import com.demai.cornel.model.TeleplayVideo;
import com.demai.cornel.reqParam.QueryTeleplayVideoParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Create By tfzhu  2022/4/30  11:10 AM
 *
 * @author tfzhu
 */
public interface TeleplayVideoDao {
    void update(TeleplayVideo teleplayVideo);

    void save(TeleplayVideo teleplayVideo);

    List<TeleplayVideo> queryTeleplayVideoList(QueryTeleplayVideoParam param);

    TeleplayVideo queryTeleplayVideoById(@Param("id")Long id);

    TeleplayVideo queryTeleplayVideoFirstVideo();

    Integer queryTeleplayVideoAllNum(QueryTeleplayVideoParam param);

    List<TeleplayVideo> queryTeleplayVideoByTeleplayIds(@Param("teleplayIds")List<Long> teleplayIds);

}
