/**
 * Copyright (c) 2015 Qunar.com. All Rights Reserved.
 */
package com.demai.cornel.reqParam;

import com.demai.cornel.reqParam.base.BaseQueryParam;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Create By tfzhu  2022/5/1  3:14 PM
 *
 * @author tfzhu
 */
@Data @NoArgsConstructor @AllArgsConstructor @Builder public class QueryTeleplayVideoBrowseDataParam extends BaseQueryParam {

    @NotNull
    private Long videoId;

    private String startDate;

    private String endDate;
}
