package com.demai.cornel.annotation;

import java.lang.annotation.*;

/**
 * Created by tfzhu on 2019/10/30
 * @author tfzhu
 */

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Authority {
}
