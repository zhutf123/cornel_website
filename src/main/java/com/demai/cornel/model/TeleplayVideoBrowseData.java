/**
 * Copyright (c) 2015 Qunar.com. All Rights Reserved.
 */
package com.demai.cornel.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * Create By tfzhu  2022/5/2  10:31 PM
 *
 * @author tfzhu
 */
@Data @Builder @NoArgsConstructor @AllArgsConstructor public class TeleplayVideoBrowseData implements Serializable {
    private static final long serialVersionUID = 4674691421118429295L;
    private Long id;
    private Long videoId;
    private Long teleplayId;
    private Integer followNum;
    private Integer allFollowNum;
    private Integer playNum;
    private Integer allPlayNum;
    private Integer likeNum;
    private Integer allLikeNum;
    private Integer shareNum;
    private Integer allShareNum;
    private Integer commentNum;
    private Integer allCommentNum;
    private String useDay;
    private Map<String, String> extInfo;
    private Date operateTime;
    private Date createTime;
}
