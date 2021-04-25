package com.ginger.mybatisplus.practice;

import java.lang.annotation.*;

/**
 * @description: 自定义注解
 * @author: Ginger
 * @create: 2021-04-19 20:42
 **/
@Target(value = {ElementType.TYPE,ElementType.METHOD})
@Retention(value = RetentionPolicy.RUNTIME)
@Inherited
public @interface TestAnnotation {

}
@TestAnnotation
class  Test{

}