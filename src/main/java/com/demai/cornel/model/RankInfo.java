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
public class RankInfo implements Serializable {
    private static final long serialVersionUID = 6121875386244156316L;
    private Long id ;
    private String name ;
    private Integer weight ;
    private Integer status ;
    private Long operator ;
    private String operatorName ;
    private Map<String,String> extInfo ;
    private Date operateTime ;
    private Date createTime ;

    /**==========for admin,user show property=============**/
    private String statusDesc;
    private String teleplayNames;


    public void setStatus(Integer status) {
        this.status = status;
        this.statusDesc = RankInfoStatusEnum.getRankInfoStatusEnum(status, RankInfoStatusEnum.ERROR_CODE)
                .getExpr();
    }

    @AllArgsConstructor
    @NoArgsConstructor
    public static enum RankInfoStatusEnum implements IEmus {

        ERROR_CODE(-1, "未知"),
        ONLINE(1, "在线"),
        AUDITING(3, "审核中"),
        OFFLINE(2, "下线");

        @Setter @Getter
        private int value;
        @Setter @Getter
        private String expr;
        
        private static Map<Integer, RankInfoStatusEnum> rankInfoStatusEnumMap;
        static {
            rankInfoStatusEnumMap = Maps.newHashMap();
            for (RankInfoStatusEnum code : RankInfoStatusEnum.values()) {
                rankInfoStatusEnumMap.put(code.getValue(), code);
            }
        }

        public static RankInfoStatusEnum getRankInfoStatusEnum(Integer value, RankInfoStatusEnum def) {
            RankInfoStatusEnum p = rankInfoStatusEnumMap.get(value);
            if (null != p) {
                return p;
            } else {
                return def;
            }
        }

    }

    
}

