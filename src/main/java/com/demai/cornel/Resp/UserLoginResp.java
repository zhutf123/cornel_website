/**
 * Copyright (c) 2015 Qunar.com. All Rights Reserved.
 */
package com.demai.cornel.Resp;

import com.demai.cornel.dmEnum.IEmus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author tfzhu
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserLoginResp implements Serializable {
    /** 微信openId */
    private String openId;
    /** 用户id */
    private String userId;
    /** 用户角色信息 */
    private Integer role;
    /** 用户code */
    private Integer code;

    /**登录手机号**/
    private String phone;

    /**  用户当前的状态 **/
    private Integer userStatus=0;

    public UserLoginResp(String openId, String userId, Integer role, Integer code, String phone) {
        this.openId = openId;
        this.userId = userId;
        this.role = role;
        this.code = code;
        this.phone = phone;
    }

    public UserLoginResp(String openId, String userId, Integer role, Integer code, String phone, Integer userStatus) {
        this.openId = openId;
        this.userId = userId;
        this.role = role;
        this.code = code;
        this.phone = phone;
        this.userStatus = userStatus;
    }

    public static enum CODE_ENUE implements IEmus {
        NO_USER(-1, "用户不存在，请先注册"),
        SUCCESS(0, "请求成功"),
        NETWORK_ERROR(1, "网络异常，请稍后重试"),
        OPENID_ERROR(2, "opendId获取失败"),
        MSG_CODE_ERROR(3, "短信验证码错误");

        private int value;
        private String expr;

        private CODE_ENUE(int value, String expr) {
            this.value = value;
            this.expr = expr;
        }

        @Override
        public int getValue() {
            return value;
        }

        @Override
        public String getExpr() {
            return expr;
        }

        public static CODE_ENUE getByValue(Integer value) {
            for (CODE_ENUE x : CODE_ENUE.values()) {
                if (x.getValue() == value) {
                    return x;
                }
            }
            return null;
        }

    }
    public static enum USER_STATUS_ENUE implements IEmus {
        REGISTERED(1, "已注册"),
        UNREGISTERED(0, "未注册");

        private int value;
        private String expr;

        private USER_STATUS_ENUE(int value, String expr) {
            this.value = value;
            this.expr = expr;
        }

        @Override
        public int getValue() {
            return value;
        }

        @Override
        public String getExpr() {
            return expr;
        }

        public static CODE_ENUE getByValue(Integer value) {
            for (CODE_ENUE x : CODE_ENUE.values()) {
                if (x.getValue() == value) {
                    return x;
                }
            }
            return null;
        }

    }
}
