package com.demai.cornel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

/**
 * @author tfzhu
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleInfo implements Serializable {
    private static final long serialVersionUID = -6717916328374105120L;
    private Long id;
    private String userId;
    private String roleId;
    private Integer status;
    private Map<String, String> extInfo;
    private String createTime;
    private String operateTime;
}