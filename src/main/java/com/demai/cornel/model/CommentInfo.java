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
 * Create By tfzhu  2022/5/4  5:02 PM
 *
 * @author tfzhu
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentInfo implements Serializable {
    private static final long serialVersionUID = -4837517589412587399L;
    private Long id ;
    private String content ;
    private String userId ;
    private String teleplayId ;
    private Long videoId ;
    private String parentPath ;
    private Integer level ;
    private Integer top ;
    private Integer replyNum ;
    private Integer likeNum ;
    private Integer bulletChat ;
    private Integer weight ;
    private Integer status ;
    private Integer systemStatus ;
    private Integer operatorStatus ;
    private Long operator ;
    private String operatorName ;
    private Map<String,String> extInfo ;
    private Date operateTime ;
    private Date createTime ;
}
