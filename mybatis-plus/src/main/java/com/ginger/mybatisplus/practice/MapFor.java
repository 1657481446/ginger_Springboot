package com.ginger.mybatisplus.practice;

import java.util.*;

/**
 * @description: 遍历集合的方式
 * @author: Mr.Wang
 * @create: 2021-04-13 11:52
 **/
public class MapFor {
    public static void main(String[] args) {
        List<Map<String,Object>> list = new ArrayList<>();
        Map<String,Object> map = new HashMap<>(3);
        map.put("1",1);
        map.put("2",2);
        map.put("3",3);
        list.add(map);
        /**
         *foreach的entrySet
         */
        Set<Map.Entry<String, Object>> entries = map.entrySet();
        for (Map.Entry<String, Object> entry : entries) {
            System.out.println(entry.getKey()+entry.getValue());
        };
        /**
         * 迭代器的entrySet
         */
        Iterator<Map.Entry<String, Object>> iterator = map.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<String, Object> next = iterator.next();
            System.out.println(next.getKey());
            System.out.println(next.getValue());
        }
        /**
         * foreach的keySet
         */
        Set<String> strings = map.keySet();
        for (String string : strings) {
            System.out.println(string+map.get(string));
        }
        /**
         * 迭代器的keySet
         */
        Iterator<String> iterator1 = map.keySet().iterator();
        while(iterator1.hasNext()){
            String next = iterator1.next();
            System.out.println(next+""+map.get(next));

        }
        /**
         * lambda表达式
         */
        map.forEach((key,value) -> {
            System.out.println(key +value);
        });
        /**
         * stream串行流foreach
         */
        map.entrySet().stream().forEach(entry -> System.out.println(entry.getValue()+""+entry.getValue()));
        /**
         * stream并行流foreach
         */
        map.entrySet().stream().parallel().forEach(entry ->System.out.println(entry.getValue()+""+entry.getValue()));
    }
}
