package com.demai.cornel.reqParam;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

/**
 * @Author binz.zhang
 * @Date: 2019-12-30    15:15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRegisterParam implements Serializable {
    /**
     * 昵称
     */
    private String nickName;

    /**
     * 头像
     */
    private String headImg;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 微信openId
     */
    @NonNull
    private String openId;

}
