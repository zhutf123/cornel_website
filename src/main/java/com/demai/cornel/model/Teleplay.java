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
import java.util.List;
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
    private String mainSource;
    private String title;
    private String depict;
    private List<String> channel;
    private Integer nums;
    private Integer vip;
    private Integer status;
    private Date operateTime;
    private Long operator;
    private String operatorName;
    private Integer followNum;
    private Integer playNum;
    private Integer likeNum;
    private Integer shareNum;
    private Integer commentNum;
    private Integer recommend;
    private Integer top;
    private Map<String, String> extInfo;
    private Date createTime;

    /**==========for admin,user show property=============**/
    private String statusDesc;
    private List<String> channelDesc;


    @AllArgsConstructor
    @NoArgsConstructor
    public static enum TeleplayStatusEnum implements IEmus {

        ERROR_CODE(-1, "未知"),
        ONLINE(1, "在线"),
        AUDITING(3, "审核中"),
        OFFLINE(2, "下线");

        @Setter @Getter
        private int value;
        @Setter @Getter
        private String expr;
        
        private static Map<Integer, TeleplayStatusEnum> teleplayStatusEnumMap;
        static {
            teleplayStatusEnumMap = Maps.newHashMap();
            for (TeleplayStatusEnum code : TeleplayStatusEnum.values()) {
                teleplayStatusEnumMap.put(code.getValue(), code);
            }
        }

        public static TeleplayStatusEnum getTeleplayStatusEnum(Integer value, TeleplayStatusEnum def) {
            TeleplayStatusEnum p = teleplayStatusEnumMap.get(value);
            if (null != p) {
                return p;
            } else {
                return def;
            }
        }

    }

    
}

