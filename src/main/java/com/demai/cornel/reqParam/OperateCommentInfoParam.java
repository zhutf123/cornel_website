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
    private String mainImage;
    private String mainSource;
    private String videoUrl;
    private String videoSource;
    private Long videoId;
    private String title;
    private String depict;
    private Integer type;
    private Integer status;
    private Integer seq;

}
