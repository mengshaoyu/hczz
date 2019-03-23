package com.shield.hczz.caseinfo.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Excel导入导出字段
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelField {

    /**
     * excel对应的映射字段
     */
    String field() default "";

    /**
     * setter方法名
     */
    String setMethod() default "";

    /**
     * getter方法名
     */
    String getMethod() default "";

}
