/**
 * Copyright (c) 2015 Qunar.com. All Rights Reserved.
 */
package com.demai.cornel.model;

import com.demai.cornel.dmEnum.IEmus;
import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Create By tfzhu  2022/5/4  5:02 PM
 *
 * @author tfzhu
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentInfo implements Serializable {
    private static final long serialVersionUID = -4837517589412587399L;
    private Long id ;
    private String content ;
    private Long userId ;
    private Long teleplayId ;
    private Long videoId ;
    private String parentPath ;
    private Integer level ;
    private Integer top ;
    private Integer replyNum ;
    private Integer likeNum ;
    private Integer bulletChat ;
    private Integer weight ;
    private Integer status ;
    private Integer systemStatus ;
    private Integer operatorStatus ;
    private Long operator ;
    private String operatorName ;
    private Map<String,String> extInfo ;
    private Date operateTime ;
    private Date createTime ;



    /**==========for admin,user show property=============**/
    private String statusDesc;


    public void setStatus(Integer status) {
        this.status = status;
        this.statusDesc = CommentInfoStatusEnum.getCommentStatusEnum(status, CommentInfoStatusEnum.ERROR_CODE)
                .getExpr();
    }


    @AllArgsConstructor
    @NoArgsConstructor
    public static enum CommentInfoStatusEnum implements IEmus {

        ERROR_CODE(-1, "未知"),
        ONLINE(1, "在线"),
        AUDITING(3, "审核中"),
        OFFLINE(2, "下线");

        @Setter @Getter
        private int value;
        @Setter @Getter
        private String expr;

        private static Map<Integer, CommentInfoStatusEnum> commentInfoStatusEnumMap;
        static {
            commentInfoStatusEnumMap = Maps.newHashMap();
            for (CommentInfoStatusEnum code : CommentInfoStatusEnum.values()) {
                commentInfoStatusEnumMap.put(code.getValue(), code);
            }
        }

        public static CommentInfoStatusEnum getCommentStatusEnum(Integer value, CommentInfoStatusEnum def) {
            CommentInfoStatusEnum p = commentInfoStatusEnumMap.get(value);
            if (null != p) {
                return p;
            } else {
                return def;
            }
        }

    }
}
