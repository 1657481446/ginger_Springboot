package com.ginger.mybatisplus.practice.java8;

import com.ginger.mybatisplus.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @description: Stream中的规约
 * @author: Mr.Wang
 * @create: 2021-04-02 16:56
 **/
public class StreamReduce {
    public static void main(String [] args) {
        List<User> list = new ArrayList<>();
        list.add(new User("1","A","A",24,"M"));
        list.add(new User("2","B","B",21,"F"));
        list.add(new User("3","C","C",27,"M"));
        list.add(new User("4","D","D",31,"F"));
        list.add(new User("5","E","E",20,"F"));

        /*第一种reduce方法 T reduce(T identity, BinaryOperator<T> accumulator);
        第一个参数是初始值也表明返回值类型   第二个参数是对流中每个元素进行操作的lambda表达式  x为阶段性累加结果 y是流中的下一个元素   从初始值开始拼接流中的每一个元素*/
        String reduce2 = list.stream().map(User::getSex).filter(x -> x.equals("F")).reduce("女生", (x, y) -> x.concat(y));
        System.out.println(reduce2);
        /*第二种reduce方法 Optional<T> reduce(BinaryOperator<T> accumulator);
        参数是对流中每个元素进行操作的lambda表达式  x为阶段性累加结果 y是流中的下一个元素   从初始值开始累加流中的每一个元素*/
        Optional<Integer> reduce3 = list.stream().map(User::getAge).reduce((x, y) -> x + y);
        System.out.println(reduce3);
        /*第三种方法 <U> U reduce(U identity,  BiFunction<U, ? super T, U> accumulator,BinaryOperator<U> combiner); 适合并行流
        第一个参数 初始值也表明返回值类型 第二个参数对流中每个元素进行操作的lambda表达式 第三个参数 对多个线程累加的结果进行汇总*/
        Integer reduce = list.parallelStream().map(User::getAge).reduce(0, (x, y) -> x + y, (x, y) -> x * y);
        System.out.println(reduce);

        // 求最大值
        Optional<Integer> reduce1 = list.stream().map(User::getAge).reduce((x, y) -> {

            return  x >y ? x : y;
        });
        Optional<Integer> reduce4 = list.stream().map(User::getAge).reduce(Integer::max);
        System.out.println(reduce1.get());
        System.out.println(reduce4.get());

    }
}
