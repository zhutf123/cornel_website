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
 * Create By tfzhu  2022/5/3  6:24 AM
 *
 * @author tfzhu
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChannelGroup implements Serializable {
    private static final long serialVersionUID = -6222158566343449460L;
    private Long id ;
    private String name ;
    private Integer recommend ;
    private List<String> channel;
    private Integer status ;
    private Date operateTime ;
    private Long operator ;
    private String operatorName ;
    private Map<String,String> extInfo ;
    private Date createTime ;

    /**==========for admin,user show property=============**/
    private String statusDesc;
    private Integer channelSize;
    private List<Channel> channelList;

    public void setStatus(Integer status) {
        this.status = status;
        this.statusDesc = ChannelGroupStatusEnum.getChannelGroupStatusEnum(status, ChannelGroupStatusEnum.ERROR_CODE)
                .getExpr();
    }


    @AllArgsConstructor
    @NoArgsConstructor
    public static enum ChannelGroupStatusEnum implements IEmus {

        ERROR_CODE(-1, "未知"),
        ONLINE(1, "在线"),
        AUDITING(3, "审核中"),
        OFFLINE(2, "下线"),
        DELETE(4, "删除")
        ;

        @Setter @Getter
        private int value;
        @Setter @Getter
        private String expr;

        private static Map<Integer, ChannelGroupStatusEnum> channelGroupStatusEnumMap;
        static {
            channelGroupStatusEnumMap = Maps.newHashMap();
            for (ChannelGroupStatusEnum code : ChannelGroupStatusEnum.values()) {
                channelGroupStatusEnumMap.put(code.getValue(), code);
            }
        }

        public static ChannelGroupStatusEnum getChannelGroupStatusEnum(Integer value, ChannelGroupStatusEnum def) {
            ChannelGroupStatusEnum p = channelGroupStatusEnumMap.get(value);
            if (null != p) {
                return p;
            } else {
                return def;
            }
        }

    }

}


