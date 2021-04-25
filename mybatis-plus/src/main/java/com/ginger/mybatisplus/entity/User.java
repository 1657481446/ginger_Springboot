package com.ginger.mybatisplus.entity;

import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description:
 * @author: Ginger
 * @create: 2021-04-21 15:13
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String id;
    private String userName;
    private String passWord;
    private int age;
    private String sex;
}
