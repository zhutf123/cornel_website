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
import java.util.Map;

/**
 * Create By tfzhu  2022/4/30  11:05 AM
 *
 * @author tfzhu
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Teleplay implements Serializable {
    private static final long serialVersionUID = 2272768995014657918L;
    private Long id;
    private String mainImage;
    private String title;
    private String desc;
    private Integer nums;
    private Integer vip;
    private Integer status;
    private Date operateTime;
    private Long operator;
    private Integer followNum;
    private Integer playNum;
    private Integer likeNum;
    private Integer shareNum;
    private Integer commentNum;
    private Integer recommend;
    private Integer top;
    private Map<String, String> extInfo;
    private Date createTime;
}

