/**
 * Copyright (c) 2015 Qunar.com. All Rights Reserved.
 */
package com.demai.cornel.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * Create By tfzhu  2022/5/4  5:19 AM
 *
 * @author tfzhu
 */
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class UserPayInfo implements Serializable {
    private static final long serialVersionUID = -6306214007447469264L;
    private Long id;
    private Long userId;
    private Long orderId;
    private Long productId;
    private String productName;
    private BigDecimal money;
    private Integer status;
    private Date payTime;
    private Map<String, String> extInfo;
    private Date createTime;
}
