/**
 * Copyright (c) 2015 Qunar.com. All Rights Reserved.
 */
package com.demai.cornel.reqParam;

import com.demai.cornel.reqParam.base.BaseQueryParam;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Create By tfzhu  2022/5/1  3:14 PM
 *
 * @author tfzhu
 */
@Data @NoArgsConstructor @AllArgsConstructor @Builder public class QueryTeleplayParam extends BaseQueryParam {

    private Long id;
    private Boolean vip;
    private String channel;
    private Integer status;
    private String title;
}
