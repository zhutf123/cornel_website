/**
 * Copyright (c) 2015 Qunar.com. All Rights Reserved.
 */
package com.demai.cornel.reqParam;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * Create By tfzhu  2022/5/1  4:23 PM
 *
 * @author tfzhu
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OperateChannelGroupParam implements Serializable {
    private String name;
    private Integer recommend;
    List<String> channel;
    private Integer status;

    private Long id;


}
