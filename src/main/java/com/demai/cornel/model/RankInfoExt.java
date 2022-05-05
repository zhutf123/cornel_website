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
 * Create By tfzhu  2022/5/6  6:48 AM
 *
 * @author tfzhu
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RankInfoExt implements Serializable {

    private static final long serialVersionUID = -6626186358686352514L;
    private Long id ;
    private Integer weight ;
    private Long teleplayId ;
    private Long rankInfoId ;
    private Integer status ;
    private Long operator ;
    private String operatorName ;
    private Map<String,String> extInfo ;
    private Date operateTime ;
    private Date createTime ;

    /**==========for admin,user show property=============**/
    private String teleplayName;

}
