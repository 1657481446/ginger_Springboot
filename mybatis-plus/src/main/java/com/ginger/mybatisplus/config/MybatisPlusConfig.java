package com.ginger.mybatisplus.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.optimize.JsqlParserCountOptimize;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description: MybatisPlus配置类
 * @author: Ginger
 * @create: 2021-04-25 19:50
 **/
@Configuration
@MapperScan("com.ginger.mybatisplus.Mapper;")
public class MybatisPlusConfig {

    /**
     * 3.4.0之前的分页插件
     * @return
     */
    @Bean
    public PaginationInterceptor mybatisPlusInterceptor(){
        PaginationInterceptor interceptor = new PaginationInterceptor();
        interceptor.setCountSqlParser(new JsqlParserCountOptimize(true));
        return interceptor;
    }
}
