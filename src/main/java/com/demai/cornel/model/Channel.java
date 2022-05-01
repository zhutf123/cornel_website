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
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Channel implements Serializable {
    private static final long serialVersionUID = -6368982069631479983L;
    private Long id;
    private String name;
    private Integer type;
    private Integer weight;
    private Integer status;
    private Date operateTime;
    private Long operator;
    private String operatorName;
    private Map<String, String> extInfo;
    private Date createTime;

    @AllArgsConstructor
    @NoArgsConstructor
    public static enum ChannelTypeEnum implements IEmus {

        TELEPLAY_TAG(0, "剧集标签"),
        CHANNEL_TAG(1, "渠道标签"),
        GROUP_TAG(2, "聚合标签"),
        OTHER_GAG(2, "其他标签");

        @Setter @Getter
        private int value;
        @Setter @Getter
        private String expr;

        private static Map<Integer, ChannelTypeEnum> channelTypeEnumMap;
        static {
            channelTypeEnumMap = Maps.newHashMap();
            for (ChannelTypeEnum code : ChannelTypeEnum.values()) {
                channelTypeEnumMap.put(code.getValue(), code);
            }
        }

        public static ChannelTypeEnum getChannelTypeEnum(Integer value, ChannelTypeEnum def) {
            ChannelTypeEnum p = channelTypeEnumMap.get(value);
            if (null != p) {
                return p;
            } else {
                return def;
            }
        }

    }
}
