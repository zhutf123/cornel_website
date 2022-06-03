/**
 * Copyright (c) 2015 Qunar.com. All Rights Reserved.
 */
package com.demai.cornel.Resp;

import com.demai.cornel.model.TeleplayVideo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * Create By tfzhu  2022/5/4  6:09 AM
 *
 * @author tfzhu
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SuggestTeleplayResp implements Serializable {
    private String mainImage;
    private String mainSource;
    private String title;
    private String depict;
    private Long id;

    List<TeleplayVideo> videoList;
}
