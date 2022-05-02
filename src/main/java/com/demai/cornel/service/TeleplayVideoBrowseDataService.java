/**
 * Copyright (c) 2015 Qunar.com. All Rights Reserved.
 */
package com.demai.cornel.service;

import com.demai.cornel.dao.TeleplayVideoBrowseDataDao;
import com.demai.cornel.model.TeleplayVideoBrowseData;
import com.demai.cornel.reqParam.QueryTeleplayVideoBrowseDataParam;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Create By tfzhu  2022/5/1  3:16 PM
 *
 * @author tfzhu
 */
@Service @Slf4j public class TeleplayVideoBrowseDataService {

    @Resource private TeleplayVideoBrowseDataDao teleplayVideoBrowseDataDao;

    public List<TeleplayVideoBrowseData> getTeleplayVideoBrowseDataList(QueryTeleplayVideoBrowseDataParam param) {
        try {
            List<TeleplayVideoBrowseData> teleplayList = teleplayVideoBrowseDataDao.getTeleplayVideoBrowseDataList(param);
            return teleplayList;
        } catch (Exception e) {
            log.error("查询剧集详情数据list异常", e);
        }
        return Lists.newArrayList();
    }

    public Integer getTeleplayVideoBrowseDataAllNum(QueryTeleplayVideoBrowseDataParam param) {
        try {
            return teleplayVideoBrowseDataDao.getTeleplayVideoBrowseDataAllNum(param);
        } catch (Exception e) {
            log.error("查询剧集详情数据num异常", e);
        }
        return 0;
    }

}
