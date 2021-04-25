package com.ginger.mybatisplus.practice.java8;



import com.ginger.mybatisplus.entity.User;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @description: Stream的收集  可以收集成一个值也可以是收集成一个新的集合
 * @author: Mr.Ginger
 * @create: 2021-04-06 16:53
 **/
public class StreamCollect {
    public static void main(String[] args) {
        List<User> list = new ArrayList<>();
        list.add(new User("1","A","A",24,"M"));
        list.add(new User("2","B","B",26,"F"));
        list.add(new User("3","C","C",27,"M"));
        list.add(new User("4","D","D",31,"F"));
        list.add(new User("5","E","E",20,"F"));
        // 将流中的数据转换成List集合
        List<String> f = list.stream().map(User::getSex).filter(x -> x.equals("F")).collect(Collectors.toList());
        // 将流中的数据转换成Set集合
        Set<Integer> collect = list.stream().map(User::getAge).filter(x -> x > 1).collect(Collectors.toSet());
        // 将流中的数据转换成Map集合
        Map<String, String> collect1 = list.stream().collect(Collectors.toMap(User::getId, User::getUserName));
        // 求元素最大值、最小值、平均值、求元素个数
        Optional<Integer> collect3 = list.stream().map(User::getAge).collect(Collectors.maxBy(Integer::compareTo));
        Integer collect7 = list.stream().filter(x -> x.getAge() >1 ).collect(Collectors.summingInt(User::getAge));
        Optional<Integer> collect4 = list.stream().map(User::getAge).collect(Collectors.minBy(Integer::compareTo));
        Double collect5 = list.stream().collect(Collectors.averagingInt(User::getAge));
        Long collect2 = list.stream().map(User::getAge).filter(x -> x > 1).collect(Collectors.counting());
        // 一次性统计个数、最值、平均值   IntSummaryStatistics{count=5, sum=128, min=20, average=25.600000, max=31}
        IntSummaryStatistics collect6 = list.stream().collect(Collectors.summarizingInt(User::getAge));
        collect6.getAverage();collect6.getCount();collect6.getMax();collect6.getMin();collect6.getSum();
        System.out.println(collect6);

        // 按照条件分组   true 符合条件的  false 不符合条件的
        Map<Boolean, List<User>> collect8 = list.stream().filter(x -> x.getAge() > 1).collect(Collectors.partitioningBy(x -> x.getAge() > 10));
        // 按照值分组   key 分组的字段  value User
        Map<String, List<User>> collect9 = list.stream().filter(x -> x.getAge() > 1).collect(Collectors.groupingBy(User::getSex));
        // 按照多个条件分组 
        Map<String, Map<Integer, List<User>>> collect10 = list.stream().filter(x -> x.getAge() > 1).collect(Collectors.groupingBy(User::getSex, Collectors.groupingBy(User::getAge)));

        // 结合 joining
        String collect11 = list.stream().map(User::getUserName).collect(Collectors.joining("***"));
        System.out.println(collect11);

        // 归约 reducing
        Optional<Integer> collect12 = list.stream().map(User::getAge).collect(Collectors.reducing((x, y) -> x + y));
        Integer collect14 = list.stream().map(User::getAge).collect(Collectors.reducing(0, (x, y) -> x + y));
        /*Collectors.reducing()和stream.reduce() 不同之处*/
        Integer collect13 = list.stream().collect(Collectors.reducing(0, User::getAge, (x, y) -> x + y));
        Integer reduce = list.stream().map(User::getAge).reduce(0, (x, y) -> x + y);

    }
}
