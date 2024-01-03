package com.thgykj.router.core.handler.annotation;

import java.lang.annotation.*;

/**
 * Description 注解
 * DATA 2024-01-02
 *
 * @Author ttt
 */

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface XxlJob {

    /**
     * jobhandler name
     */
    String value();

    /**
     * init handler, invoked when JobThread init
     */
    String init() default "";

    /**
     * destroy handler, invoked when JobThread destroy
     */
    String destroy() default "";
}
