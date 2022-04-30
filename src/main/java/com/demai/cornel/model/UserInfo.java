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
 * Create By tfzhu  2022/4/30  3:52 PM
 *
 * @author tfzhu
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo implements Serializable {
    private static final long serialVersionUID = -3017187864697442385L;
    private Long id;
    private String userId;
    private String openId;
    private String name;
    private Integer gender;
    private String birthday;
    private String headImg;
    private String nickName;
    private Integer idType;
    private String idCard;
    private String termValidity;
    private String mobile;
    private Long score;
    private Integer status;
    private Map<String, String> extInfo;
    private Date lastLoginTime;
    private Date createTime;
    private Date operateTime;
    private Integer role;
}
