/**
 * Copyright (c) 2015 Qunar.com. All Rights Reserved.
 */
package com.demai.cornel.model;

import com.demai.cornel.dmEnum.IEmus;
import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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


    @AllArgsConstructor
    @NoArgsConstructor
    public static enum BannerInfoExtStatusEnum implements IEmus {

        ERROR_CODE(-1, "未知"),
        ONLINE(1, "在线"),
        AUDITING(3, "审核中"),
        OFFLINE(2, "下线");

        @Setter @Getter
        private int value;
        @Setter @Getter
        private String expr;

        private static Map<Integer, BannerInfoExtStatusEnum> bannerInfoExtStatusEnumMap;
        static {
            bannerInfoExtStatusEnumMap = Maps.newHashMap();
            for (BannerInfoExtStatusEnum code : BannerInfoExtStatusEnum.values()) {
                bannerInfoExtStatusEnumMap.put(code.getValue(), code);
            }
        }

        public static BannerInfoExtStatusEnum getBannerInfoExtStatusEnum(Integer value, BannerInfoExtStatusEnum def) {
            BannerInfoExtStatusEnum p = bannerInfoExtStatusEnumMap.get(value);
            if (null != p) {
                return p;
            } else {
                return def;
            }
        }

    }

}
