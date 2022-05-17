/**
 * Copyright (c) 2015 Qunar.com. All Rights Reserved.
 */
package com.demai.cornel.reqParam;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Create By tfzhu  2022/5/1  4:23 PM
 *
 * @author tfzhu
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OperateTeleplayParam implements Serializable {
    private Long id;
    private String mainImage;
    private String mainSource;
    private String title;
    private String depict;
    private List<String> channel;
    private Integer nums;
    private Integer vip;
    private Integer status;
    private Integer recommend;
    private Integer top;
    

}
