/**
 * Copyright (c) 2015 Qunar.com. All Rights Reserved.
 */
package com.demai.cornel.reqParam;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Create By tfzhu  2022/5/1  4:23 PM
 *
 * @author tfzhu
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OperateCommentInfoParam implements Serializable {
    private Long id;
    private Long teleplayId;
    private Long videoId;
    private String title;
    private String depict;
    private String content;
    private Integer status;
    private String path;
    private Integer level;

}
