package com.demai.cornel.vo.uploadfile;

import com.demai.cornel.dmEnum.IEmus;
import lombok.Builder;
import lombok.Data;

/**
 * @Author tfzhu
 * @Date: 2020-01-17    17:41
 */
@Data @Builder public class UploadResp {
    private Integer optResult;
    private String url;
    private Integer index;
    public static enum CODE_ENUE implements IEmus {
        SUCCESS(0, "请求成功"),
        PARAM_ERROR(1, "参数错误"),
        SERVER_ERROR(2, "服务器异常");

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

    }
}
