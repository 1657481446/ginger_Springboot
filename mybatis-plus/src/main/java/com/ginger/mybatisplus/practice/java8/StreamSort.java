package com.ginger.mybatisplus.practice.java8;

import com.ginger.mybatisplus.entity.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description: Stream流排序
 * @author: Mr.Wang
 * @create: 2021-04-01 19:52
 **/
public class StreamSort {
    public static void main(String[] args) {
        List<String>  list = Arrays.asList("A","B","a","C");
        list.stream().sorted().forEach(System.out::println);
        System.out.println("=====================");
        list.stream().limit(5).collect(Collectors.toList()).forEach(System.out::println);
        list.stream().limit(4).collect(Collectors.toList()).forEach(System.out::println);
        System.out.println("=====================");
        // 无字母大小排序
        list.sort(String.CASE_INSENSITIVE_ORDER);
        System.out.println(list.toString());
        list.stream().sorted(String::compareToIgnoreCase).forEach(System.out::print);
        // 自然排序  大写字母 》 小写字母
        list.sort(Comparator.naturalOrder());
        System.out.println(list.toString());
        list.stream().sorted(String::compareTo).forEach(System.out::print);

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
        // 先按照用户的名称排序 在按照年龄排序
        userList.stream().sorted(
                Comparator.comparing(User::getSex)
                .thenComparing(User::getAge)
        ).forEach(System.out::println);
        System.out.println("=============================================");
        // 先按照用户的名称倒叙 在按照年龄排序
        userList.stream().sorted(
                Comparator.comparing(User::getSex)
                .reversed()
                .thenComparing(User::getAge)
        ).forEach(System.out::println);
        System.out.println("=============================================");
        // 先按照用户的名称排序  在按照年龄倒叙
        userList.stream().sorted(
                Comparator.comparing(User::getSex)
                        .reversed()
                        .thenComparing(User::getAge)
                        .reversed()
        ).forEach(System.out::println);
        System.out.println("=============================================");
        // 先按照用户的名称倒叙  在按照年龄倒叙
        userList.stream().sorted(
                Comparator.comparing(User::getSex)
                        .thenComparing(User::getAge)
                        .reversed()
        ).forEach(System.out::println);
    }
}
