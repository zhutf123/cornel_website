/**
 * Copyright (c) 2015 Qunar.com. All Rights Reserved.
 */
package com.demai.cornel.Resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Create By tfzhu  2022/5/8  8:34 AM
 *
 * @author tfzhu
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserWatchAndFollowVideoResp implements Serializable {

    private String mainImage;
    private String mainSource;
    private Long videoId;
    private String title;

    /**更新信息*/
    private String  updateTips;
    /**观看信息*/
    private String  watchTips;


}
