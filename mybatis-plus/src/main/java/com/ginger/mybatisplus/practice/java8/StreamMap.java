package com.ginger.mybatisplus.practice.java8;


import java.util.*;

/**
 * @Classname StreamMap
 * @Description
 * @Date 2021/3/26 19:29
 * @Created by jiangwg
 */
public class StreamMap {

    public static void main(String[] args) {
        List<Map<String,Object>> mapList = new ArrayList<>();
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("1","Aa1");
        map.put("2","Bb2");
        map.put("3","Cc3");
        mapList.add(map);
        List<String> stringList = Arrays.asList("1","2");

        stringList.parallelStream().sorted().forEach(System.out::println);

/*
        List<String> collect = mapList.stream().map(x -> map.toString().toUpperCase().replaceAll("[^0-9]", "")).collect(Collectors.toList());

        mapList.stream().peek(x -> {
            map.toString().toUpperCase().replaceAll("[^0-9]", "");
        });
        mapList.stream().map(x -> {
            map.toString().toUpperCase().replaceAll("[^0-9]", "");
            return Stream.of(map);
        });
        stringList.stream().mapToInt( x -> Integer.parseInt(x)).forEach(System.out::println);

        stringList.stream().mapToDouble( x -> Integer.parseInt(x)).forEach(System.out::println);
        stringList.stream().mapToDouble( x -> Double.parseDouble(x)).forEach(System.out::println);

        stringList.stream().mapToLong( x -> Integer.parseInt(x)).forEach(System.out::println);

        mapList.stream().flatMap(x -> {
            x.put("111","111'");
            return Stream.of(x);
        }).forEach(System.out ::println);

        System.out.println(stringList);
        System.out.println(mapList);
        Stream.generate(Math::random).limit(10).sorted(new Comparator<Double>() {
            @Override
            public int compare(Double o1, Double o2) {
                return o2.compareTo(o1);
            }
        }).collect(Collectors.toList()).forEach(System.out :: println);*/
    }
}
