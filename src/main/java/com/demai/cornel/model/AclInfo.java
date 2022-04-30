package com.demai.cornel.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * @author tfzhu
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AclInfo implements Serializable {
    private static final long serialVersionUID = -3278874872049402020L;
    private Integer id ;
    private String name ;
    private String code ;
    private String url ;
    private Integer module ;
    private Integer status ;
    private Map<String,String> extInfo ;
    private Date createTime ;
    private Date operateTime ;
}
