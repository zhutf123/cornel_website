/**
 * Copyright (c) 2015 Qunar.com. All Rights Reserved.
 */
package com.demai.cornel.Resp;

/**
 * Create By tfzhu  2022/6/8  04:41
 *
 * @author tfzhu
 */

import com.demai.cornel.model.UserSignInInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserSignInfoResp implements Serializable {

    private List<UserSignInInfo> signInList;
    private Integer days;

    /**
     * 是否已经签到过
     */
    private Boolean signInEd = Boolean.FALSE;
    
}
