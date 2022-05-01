/**
 * Copyright (c) 2015 Qunar.com. All Rights Reserved.
 */
package com.demai.cornel.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Create By tfzhu  2022/5/2  6:34 AM
 *
 * @author tfzhu
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeleplayVideo implements Serializable {
    private static final long serialVersionUID = -4014097974902729860L;
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
    private Integer followNum;
    private Integer playNum;
    private Integer likeNum;
    private Integer shareNum;
    private Integer commentNum;


}