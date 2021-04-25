package com.ginger.mybatisplus.practice.java8;


import com.ginger.mybatisplus.entity.User;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * @description: java 8新特新所有内容
 * @author: Mr.Wang
 * @create: 2021-04-08 16:35
 **/
public class StreamAll {
   private static List<User> list = new ArrayList<>();
    static{
        list.add(new User("2","A","A",24,"M"));
        list.add(new User("2","B","B",26,"F"));
        list.add(new User("3","C","C",27,"M"));
        list.add(new User("4","D","D",31,"F"));
        list.add(new User("5","E","E",20,"F"));
    }
    private static List<Map<String,Object>> mapList = new ArrayList<>();
    static{
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("1","Aa1");
        map.put("2","Bb2");
        map.put("3","Cc3");
        mapList.add(map);
    }
    public static void main(String[] args) throws Exception {
        /**
         * lambda表达式
         */
        list.sort((x,y)->{
            if(x.getAge()>y.getAge()){
                return 1;
            }
            return -1;
        });
        /**
         * stream流
         *  将集合或数组中的元素转换成流，再对流进行聚合、排序、筛选等处理
         *      特点：Stream流不会改变数据流，经过中间操作之后会通过终止操作产生一个新的集合或值
         *          Stream流是惰性求值，延迟计算，只有调用终止操作是才会对中间操作进行处理求值
         *          Stream流不会存储数据，在经过中间计算后会输出结果
         */
        /* 创建Stream 串行流流 默认*/
        Stream<User> stream = list.stream();
        Stream<Object> stream1 = new HashSet<Object>().stream();
        Stream<HashMap<String, Object>> hashMapStream = Stream.of(new HashMap<String, Object>(1));
        Stream<String> stringStream = Arrays.stream(new String[5]);
        /*创建并行流*/
        list.parallelStream();
        list.stream().parallel();
        Stream.of(new String[5]).parallel();
        /**
         * Stream的静态方法
         */
        /*将()中的内容转换成Stream流*/
        stringStream = Stream.of(new String[5]);
        /*迭代 创建无限流 从第一个参数开始每次自增2*/
        Stream.iterate( 0,x -> x+2);
        /*生产 创建无限流*/
        Stream.generate(Math::random);
        
        /**
         * 中间操作
         */
        /* -------------------------------------------------------map  类型转换------------------------------------------------------------ */
        List<Integer> collect = list.stream().map(User::getAge).collect(Collectors.toList());
        List<Object> collect1 = mapList.stream().map(map -> {
            map.get("1");
            return Stream.of(map);
        }).collect(Collectors.toList());
        /*peek  如果传入的参数和传出的参数一直 就不用写 return*/
        List<Map<String, Object>> collect2 = mapList.stream().peek(map -> {
            map.put("4", "2");
        }).collect(Collectors.toList());
        /* flatMap 将流中的每个值都都转换成流，然后将所有的流连城一个流统一处理*/
        List<String> strList = Arrays.asList("a,f,c,d","1,2,3");
        strList.stream().flatMap(x -> {
            String[] str = x.split(",");
            return Arrays.stream(str);
        });
        DoubleStream doubleStream = list.stream().mapToDouble(user -> {
            return Double.parseDouble(user.getId());
        });
        LongStream longStream = list.stream().mapToLong(user -> {
            return Long.parseLong(user.getId());
        });
        /* -------------------------------------------------------filter  筛选和切片------------------------------------------------------------ */
        list.stream().filter(x -> x.getAge() >2).collect(Collectors.toList());
        /*去重*/
        list.stream().distinct().collect(Collectors.toList());
        /* skip 跳过   limit 取多少个*/
        List<User> collect3 = list.stream().filter(x -> x.getAge() > 1).skip(6).limit(6).collect(Collectors.toList());
        /* ------------------------------------------------------- sorted排序------------------------------------------------------------ */
        list.stream().map(User::getAge).sorted().collect(Collectors.toList());
        list.stream().sorted((x,y) -> {
            if(x.getAge() >y.getAge()){
                return 1;
            }
            return -1;
        }).collect(Collectors.toList());
        list.stream().map(User::getAge).sorted(Integer::compareTo).collect(Collectors.toList());
        /* -------------------------------------------------------遍历 查询------------------------------------------------------------ */
        list.stream().forEach( System.out ::println);
        /*查询是否都符合条件*/
        boolean b = list.stream().allMatch(user -> user.getAge() > 30);
        /*查询是否至少一个符合条件*/
        boolean b1 = list.stream().anyMatch(user -> user.getAge() > 30);
        /*查询是否都不符合条件*/
        boolean b2 = list.stream().noneMatch(user -> user.getAge() > 30);
        /*获取流中的第一个元素*/
        Optional<User> first = list.stream().findFirst();
        /*获取流中任意一个元素*/
        Optional<User> any = list.stream().findAny();
        /* -------------------------------------------------------Optional------------------------------------------------------------ */
        // 一个可以为null 的容器对象，
        // get() 获取容器中的值 如果为空 抛出异常
        first.get();
        // 判断容器是否不为空  有值返回true 为空返回false
        first.isPresent();
        // ifPresent() 如果不为空 则会执行括号中的代码
        first.ifPresent(user ->{
            user.setId("55");
        });
        // orElse() 如果存在值就返回值 如果不存在值 就返回括号中的默认值
        first.orElse(new User());
        // orElseThrow 如果存在值返回值 否则会抛出括号中的异常
        first.orElseThrow(Exception::new);
        // orElseGet 如果存在返回值 否则会调用另外的方法返回对应的值
        User user = first.orElseGet(() -> a());
        /* -------------------------------------------------------collect收集------------------------------------------------------------ */
        List<Integer> collect4 = list.stream().map(User::getAge).collect(Collectors.toList());
        // 获取最大值
        Optional<Integer> collect5 = list.stream().map(User::getAge).collect(Collectors.maxBy(Integer::compareTo));
        // 获取最小值
        Optional<Integer> collect6 = list.stream().map(User::getAge).collect(Collectors.minBy(Integer::compareTo));
        // 获取平均值
        Double collect7 = list.stream().collect(Collectors.averagingInt(User::getAge));
        // 根据partitioningBy()里面的条件进行分区   两个区  一个符合的 一个不符合的
        Map<Boolean, List<Integer>> collect8 = list.stream().map(User::getAge).collect(Collectors.partitioningBy(x -> x > 1));
        // 根据字段分组
        Map<String, List<User>> collect9 = list.stream().collect(Collectors.groupingBy(User::getSex));
        // 根据多个字段  多级分组
        list.stream().collect(Collectors.groupingBy(User::getSex,Collectors.groupingBy(User::getAge)));
        // 属性拼接分组 {24-A=[User(id=2, userName=A, passWord=A, age=24, sex=M)]}
        Map<String, List<User>> collect17 = list.stream().collect(Collectors.groupingBy(uuser -> uuser.getAge() + "-" + uuser.getUserName()));
        // 根据自定义条件分组
        list.stream().collect(Collectors.groupingBy( x ->{
            if(x.getAge() >10){
                return "10岁以下";
            }else if(10 >=x.getAge() && x.getAge() <30){
                return "10岁以上 30岁一下";
            }else{
                return "其余";
            }
        }));
        // 根据字段分组 然后对分组后的内容进行处理
        Map<String, Long> collect18 = list.stream().collect(Collectors.groupingBy(User::getId, Collectors.counting()));
        System.out.println(collect18);
        System.out.println("collect17"+collect17.toString());
        // 求和
        Integer collect10 = list.stream().collect(Collectors.summingInt(User::getAge));
        // 求流中的元素总数
        Long collect12 = list.stream().collect(Collectors.counting());
        // 一次性求和、最值、平均值、元素总数
        IntSummaryStatistics collect11 = list.stream().collect(Collectors.summarizingInt(User::getAge));
        // joining 将流中的元素用连接符连接成一个字符串  {"1","2"}
        String collect13 = list.stream().map(User::getUserName).collect(Collectors.joining(","));
        String collect14 = list.stream().map(User::getUserName).collect(Collectors.joining(",", "{", "}"));
        // reducing
        list.stream().map(User::getAge).collect(Collectors.reducing((x,y) -> x+y));
        list.stream().map(User::getAge).collect(Collectors.reducing(0,(x,y) -> x+y));
        list.stream().collect(Collectors.reducing(0,User::getAge,(x,y) -> x+y));
        // collectingAndThen对流生成的集合再次操作 
        Integer collect15 = list.stream().collect(Collectors.collectingAndThen(Collectors.groupingBy(User::getId), ss -> ss.size()));
        System.out.println(collect15);
        // mapping 对流中的每个元素进行第一个参数的操作，然后用第二个参数将处理后的元素收集起来
        List<User> collect16 = list.stream().collect(Collectors.mapping(x -> {
            x.setId("");
            return x;
        }, Collectors.toList()));
        System.out.println(collect16);
    }
    public static  User a(){
        return new User();
    }
}
