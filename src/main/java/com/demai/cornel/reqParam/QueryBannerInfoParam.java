/**
 * Copyright (c) 2015 Qunar.com. All Rights Reserved.
 */
package com.demai.cornel.reqParam;

import com.demai.cornel.reqParam.base.BaseQueryParam;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Create By tfzhu  2022/5/1  3:14 PM
 *
 * @author tfzhu
 */
@Data public class QueryBannerInfoParam extends BaseQueryParam {

    private Integer pageSize;
    private Integer pageNum = 1;
    private Integer offSet;

    public void setPageSize(Integer pageSize) {
        if (pageSize == null || pageSize == 0) {
            pageSize = 10;
        }
        this.pageSize = pageSize;
    }

    public void setPageNum(Integer pageNum) {
        if (pageNum == null || pageNum == 0) {
            pageNum = 1;
        }
        this.pageNum = pageNum;
        this.offSet = (this.pageNum - 1) * this.pageSize;
    }

}
