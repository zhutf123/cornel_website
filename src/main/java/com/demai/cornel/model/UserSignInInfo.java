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
 * Create By tfzhu  2022/6/8  05:33
 *
 * @author tfzhu
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserSignInInfo implements Serializable {
    private static final long serialVersionUID = -7258407578602120363L;
    private Long id ;
    private Long userId ;
    private Integer goldCoin ;
    private Integer status ;
    private Date signInTime ;
    private Map<String,String> extInfo ;
}
