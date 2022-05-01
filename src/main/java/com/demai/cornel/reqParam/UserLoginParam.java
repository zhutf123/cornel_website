/**
 * Copyright (c) 2015 Qunar.com. All Rights Reserved.
 */
package com.demai.cornel.reqParam;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author tfzhu
 */
@Data @AllArgsConstructor @NoArgsConstructor @Builder public class UserLoginParam implements Serializable {

    /**
     * 用户手机号
     */
    //private String phone;
    /**
     * 微信登录code
     */
    private String jscode;
    /**
     * 短信验证码
     */
    //private String msgCode;
    /**
     * 用户角色
     */
    private Integer role;
    /**
     * 用户名
     */
    private String name;
    /**
     * 密码
     */
    private String passwd;

    private Boolean admin;

}
