package com.demai.cornel.Resp;

import com.demai.cornel.dmEnum.IEmus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author binz.zhang
 * @Date: 2020-01-07    13:56
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAddUserResp implements Serializable {
    private String userId;
    private String userName;
    private String idCard;
    private String idType;
    private String mobile;
    private Integer status;

    public static enum CODE_ENUE implements IEmus {
        NO_USER(-1, "用户不存在，请先注册"),
        SUCCESS(0, "请求成功"),
        NETWORK_ERROR(1, "网络异常，请稍后重试"),
        TEL_ERROR(2, "手机号码不正确"),
        IDCARD_ERROR(3, "身份证号码有误");


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

        public static UserLoginResp.CODE_ENUE getByValue(Integer value) {
            for (UserLoginResp.CODE_ENUE x : UserLoginResp.CODE_ENUE.values()) {
                if (x.getValue() == value) {
                    return x;
                }
            }
            return null;
        }

    }
}
