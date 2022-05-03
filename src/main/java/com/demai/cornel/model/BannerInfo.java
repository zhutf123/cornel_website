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
 * Create By tfzhu  2022/5/1  4:41 PM
 *
 * @author tfzhu
 */
@Data @Builder @NoArgsConstructor @AllArgsConstructor public class BannerInfo implements Serializable {
    private static final long serialVersionUID = 5865291518157016701L;
    private Long id;
    private String mainImage;
    private String mainSource;
    private String videoUrl;
    private String videoSource;
    private Long videoId;
    private String title;
    private String depict;
    private Integer type;
    private Integer status;
    private Integer seq;
    private Date operateTime;
    private Long operator;
    private String operatorName;
    private Map<String, String> extInfo;
    private Date createTime;

    /**==========for admin,user show property=============**/
    private String statusDesc;

    
    @AllArgsConstructor
    @NoArgsConstructor
    public static enum BannerInfoStatusEnum implements IEmus {

        ERROR_CODE(-1, "未知"),
        ONLINE(1, "在线"),
        AUDITING(3, "审核中"),
        OFFLINE(2, "下线");

        @Setter @Getter
        private int value;
        @Setter @Getter
        private String expr;

        private static Map<Integer, BannerInfoStatusEnum> bannerInfoStatusEnumMap;
        static {
            bannerInfoStatusEnumMap = Maps.newHashMap();
            for (BannerInfoStatusEnum code : BannerInfoStatusEnum.values()) {
                bannerInfoStatusEnumMap.put(code.getValue(), code);
            }
        }

        public static BannerInfoStatusEnum getBannerInfoStatusEnum(Integer value, BannerInfoStatusEnum def) {
            BannerInfoStatusEnum p = bannerInfoStatusEnumMap.get(value);
            if (null != p) {
                return p;
            } else {
                return def;
            }
        }

    }
}
