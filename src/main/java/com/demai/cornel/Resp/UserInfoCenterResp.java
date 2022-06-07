/**
 * Copyright (c) 2015 Qunar.com. All Rights Reserved.
 */
package com.demai.cornel.Resp;

/**
 * Create By tfzhu  2022/6/8  04:41
 *
 * @author tfzhu
 */

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoCenterResp implements Serializable {

    private String userId;
    private String openId;
    private String name;
    private Integer gender;
    private String headImg;
    private String nickName;
    private Long goldCoin;
    private Integer vip;
    
}
