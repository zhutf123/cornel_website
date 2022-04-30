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
 * Create By tfzhu  2022/4/30  4:24 PM
 *
 * @author tfzhu
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserLoginSendMsgParam implements Serializable {
    /** 用户手机号 */
    private String phone;
}
