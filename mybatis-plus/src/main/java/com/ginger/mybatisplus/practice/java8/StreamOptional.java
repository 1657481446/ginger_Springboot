package com.ginger.mybatisplus.practice.java8;

import com.ginger.mybatisplus.entity.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @description: Stream的查找和匹配
 * @author: Mr.Wang
 * @create: 2021-04-02 11:58
 **/
public class StreamOptional {

    public static void main(String[] args) {

        List<User> userList = new ArrayList<>(5);

        for (int i = 0; i < 5; i++) {
            User user = new User();
            user.setAge(i);
            if(i<2){
                user.setSex("M");
            }else if(i >= 2 && i<=4){
                user.setSex("F");
            }else{
                user.setSex("M");
            }
            userList.add(user);
        }

        Optional<User> m = userList.stream().filter(x -> x.getSex().equals("F")).findFirst();
        // 如果值存在获取值 不存在抛出异常
        System.out.println( m.get());
        // 值存在true  不存在false
        System.out.println(m.isPresent());
        userList.stream().filter(x -> x.getSex().equals("A"))
                .findFirst()
                 /*如果值存在 就执行ifPresent(代码)括号中的代码  不存在的话不执行
                .ifPresent( x -> System.out.println(x.getAge()))*/
                /* 如果值不存在就 返回orElse(T)里面的默认值
                .orElse(null);*/
                /*如果值不存在可以抛出一个自定义异常
                .orElseThrow(RuntimeException::new);*/;

        Optional<User> f = userList.stream().findAny();
        boolean b = userList.stream().allMatch(x -> x.getAge() > -1);
        System.out.println(b);
        boolean b1 = userList.stream().anyMatch(x -> x.getAge() > 1200);
        System.out.println(b1);
        boolean b2 = userList.stream().noneMatch(x -> x.getAge() > 1200);
        System.out.println(b2);
        long count = userList.stream().count();
        System.out.println(count);
        List<Integer> intList = Arrays.asList(12,23,5,3,15);
        Integer integer = intList.stream().max(Integer::compareTo).get();
        System.out.println(integer);
        integer = intList.stream().min(Integer::compareTo).get();
        System.out.println(integer);
    }
}
