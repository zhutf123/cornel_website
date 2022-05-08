/**
 * Copyright (c) 2015 Qunar.com. All Rights Reserved.
 */
package com.demai.cornel.Resp;

import com.demai.cornel.model.Teleplay;
import com.demai.cornel.model.TeleplayVideo;
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
public class UserVideoInfoResp implements Serializable {
    private Long id;
    private Long teleplayId;
    private String mainImage;
    private String mainSource;
    private String videoUrl;
    private String videoSource;
    private String title;
    private Integer seq;
    private Long videoTime;
    private Integer vip;
    private Integer status;
    private Date operateTime;
    private Long operator;
    private String operatorName;
    private Integer recommend;
    private Map<String, String> extInfo;
    private Date createTime;

    /**==========for admin,user show property=============**/
    private String statusDesc;
    private Integer followNum;
    private Integer playNum;
    private Integer likeNum;
    private Integer shareNum;
    private Integer commentNum;

    List<TeleplayVideo> videoList;
}
