package com.zhu.demo.entity;

import java.lang.annotation.*;

/**
 * @Author: zhutao
 * @Date: 2020/9/22 16:21
 */
@Target(ElementType.METHOD) //注解放置的目标位置,METHOD是可注解在方法级别上
@Retention(RetentionPolicy.RUNTIME) //注解在哪个阶段执行
@Documented //生成文档
public @interface MyLog {
    String value() default "";
}