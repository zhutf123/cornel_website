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
 * Create By tfzhu  2022/5/10  6:33 AM
 *
 * @author tfzhu
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserWatchVideo implements Serializable {
    private static final long serialVersionUID = 7928071720281632850L;
    private Long id ;
    private Long videoId ;
    private Integer status ;
    private Map<String,String> extInfo ;
    private Date operateTime ;
    private Date createTime ;
}
