package com.sohu.bp.elite.persistence.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * create time: 2015年12月25日 下午11:52:55
 * @auther dexingyang
 */

@Target({METHOD, FIELD}) 
@Retention(RUNTIME)
public @interface Column {

	/**
     * The column length. (Applies only if a
     * string-valued column is used.)
     */
    int length() default -1;
}
