/**
 * Copyright (c) 2015 Qunar.com. All Rights Reserved.
 */
package com.demai.cornel.Resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * Create By tfzhu  2022/5/8  11:04 AM
 *
 * @author tfzhu
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRankInfoResp implements Serializable {
    private Long id ;
    private String name ;
    private Integer weight ;
    private Integer type;
    
    List<UserTeleplayResp> teleplayList;



    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static  class UserTeleplayResp implements Serializable{
        private String title;
        private String mainImage;
        private String mainSource;
        private Long teleplayId;
        private String tip;
    }
}
