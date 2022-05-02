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
 * Create By tfzhu  2022/5/2  6:34 AM
 *
 * @author tfzhu
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeleplayVideo implements Serializable {
    private static final long serialVersionUID = -4014097974902729860L;
    private Long id;
    private Long teleplayId;
    private String mainImage;
    private String mainSource;
    private String videoUrl;
    private String videoSource;
    private String title;
    private Integer seq;
    private Long videoTime;
    private Integer vip;
    private Integer status;
    private Date operateTime;
    private Long operator;
    private String operatorName;
    private Integer recommend;
    private Map<String, String> extInfo;
    private Date createTime;

    /**==========for admin,user show property=============**/
    private String statusDesc;
    private Integer followNum;
    private Integer playNum;
    private Integer likeNum;
    private Integer shareNum;
    private Integer commentNum;

    public void setStatus(Integer status) {
        this.status = status;
        this.statusDesc = TeleplayVideoStatusEnum.getTeleplayVideoStatusEnum(status, TeleplayVideoStatusEnum.ERROR_CODE)
                .getExpr();
    }


    @AllArgsConstructor
    @NoArgsConstructor
    public static enum TeleplayVideoStatusEnum implements IEmus {

        ERROR_CODE(-1, "未知"),
        ONLINE(1, "发布"),
        OFFLINE(2, "待发布");

        @Setter @Getter
        private int value;
        @Setter @Getter
        private String expr;

        private static Map<Integer, TeleplayVideoStatusEnum> teleplayVideoStatusEnumMap;
        static {
            teleplayVideoStatusEnumMap = Maps.newHashMap();
            for (TeleplayVideoStatusEnum code : TeleplayVideoStatusEnum.values()) {
                teleplayVideoStatusEnumMap.put(code.getValue(), code);
            }
        }

        public static TeleplayVideoStatusEnum getTeleplayVideoStatusEnum(Integer value, TeleplayVideoStatusEnum def) {
            TeleplayVideoStatusEnum p = teleplayVideoStatusEnumMap.get(value);
            if (null != p) {
                return p;
            } else {
                return def;
            }
        }

    }


}