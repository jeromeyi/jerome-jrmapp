package com.jrmapp.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//指定运行期间检查  
@Retention(RetentionPolicy.RUNTIME)  
//指定作用对象,可作用与Field和Method上  
@Target({ElementType.FIELD,ElementType.METHOD})  
public @interface BeanResource {  
    //设置name属性,类似@Resource(name="")中的name  
    String name() default "";  
}  
