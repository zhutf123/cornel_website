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
import java.util.List;
import java.util.Map;

/**
 * Create By tfzhu  2022/4/30  3:52 PM
 *
 * @author tfzhu
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo implements Serializable {
    private static final long serialVersionUID = -3017187864697442385L;
    private Long id;
    private String userId;
    private String openId;
    private String name;
    private String passwd;
    private Integer gender;
    private String birthday;
    private String headImg;
    private String nickName;
    private Integer idType;
    private String idCard;
    private String termValidity;
    private String mobile;
    private String mail;
    private Long score;
    private Integer status;
    private Map<String, String> extInfo;
    private Date lastLoginTime;
    private Date createTime;
    private Date operateTime;
    private Integer role;


    /**==========for admin,user show property=============**/
    private String statusDesc;
    private BigDecimal allPayMoney;


    public void setStatus(Integer status) {
        this.status = status;
        this.statusDesc = UserInfoStatusEnum.getUserInfoStatusEnum(status, UserInfoStatusEnum.ERROR_CODE)
                .getExpr();
    }



    @AllArgsConstructor
    @NoArgsConstructor
    public static enum UserInfoStatusEnum implements IEmus {

        ERROR_CODE(-1, "未知"),
        ONLINE(1, "在线"),
        AUDITING(3, "审核中"),
        OFFLINE(2, "拉黑");

        @Setter @Getter
        private int value;
        @Setter @Getter
        private String expr;

        private static Map<Integer, UserInfoStatusEnum> userInfoStatusEnumMap;
        static {
            userInfoStatusEnumMap = Maps.newHashMap();
            for (UserInfoStatusEnum code : UserInfoStatusEnum.values()) {
                userInfoStatusEnumMap.put(code.getValue(), code);
            }
        }

        public static UserInfoStatusEnum getUserInfoStatusEnum(Integer value, UserInfoStatusEnum def) {
            UserInfoStatusEnum p = userInfoStatusEnumMap.get(value);
            if (null != p) {
                return p;
            } else {
                return def;
            }
        }

    }

}
