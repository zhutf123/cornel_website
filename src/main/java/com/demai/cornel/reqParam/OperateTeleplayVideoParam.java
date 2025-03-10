/**
 * Copyright (c) 2015 Qunar.com. All Rights Reserved.
 */
package com.demai.cornel.reqParam;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

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
public class OperateTeleplayVideoParam implements Serializable {
    private Long id;
    @NonNull
    private String mainImage;
    @NonNull
    private String mainSource;
    @NonNull
    private String videoUrl;
    @NonNull
    private String videoSource;
    private String title;
    @NonNull
    private Long videoTime;
    @NonNull
    private Long teleplayId;
    @NonNull
    private Integer seq;
    private Integer vip;
    private Integer status;
    private Integer recommend;
    

}
