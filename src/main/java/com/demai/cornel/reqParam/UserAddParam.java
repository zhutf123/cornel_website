package com.demai.cornel.reqParam;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author binz.zhang
 * @Date: 2020-01-07    13:09
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserAddParam implements Serializable {
    private String userId;
    private String userName;
    private String idCard;
    private String idType;
    private String mobile;
}
