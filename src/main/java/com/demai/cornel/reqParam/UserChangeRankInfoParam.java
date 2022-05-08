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
 * Create By tfzhu  2022/5/8  8:08 PM
 *
 * @author tfzhu
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserChangeRankInfoParam extends BaseQueryParam {

    private Long rankInfoId;
}
