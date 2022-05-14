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
import java.math.BigDecimal;
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
    private Long teleplayId ;
    private Long userId ;
    private Integer status ;
    private Map<String,String> extInfo ;
    private Date operateTime ;
    private Date createTime ;


    /**==========for admin,user show property=============**/
    private Integer seq;


    @AllArgsConstructor
    @NoArgsConstructor
    public static enum UserWatchVideoStatusEnum implements IEmus {

        ERROR_CODE(-1, "未知"),
        ONLINE(1, "在线"),
        AUDITING(3, "审核中"),
        OFFLINE(2, "下线");

        @Setter @Getter
        private int value;
        @Setter @Getter
        private String expr;

        private static Map<Integer, UserWatchVideoStatusEnum> userWatchVideoStatusEnumMap;
        static {
            userWatchVideoStatusEnumMap = Maps.newHashMap();
            for (UserWatchVideoStatusEnum code : UserWatchVideoStatusEnum.values()) {
                userWatchVideoStatusEnumMap.put(code.getValue(), code);
            }
        }

        public static UserWatchVideoStatusEnum getUserWatchVideoStatusEnum(Integer value, UserWatchVideoStatusEnum def) {
            UserWatchVideoStatusEnum p = userWatchVideoStatusEnumMap.get(value);
            if (null != p) {
                return p;
            } else {
                return def;
            }
        }

    }
}
