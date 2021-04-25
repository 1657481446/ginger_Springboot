package com.ginger.mybatisplus.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @description: mybatis 配置类
 * @author: Ginger
 * @create: 2021-04-16 10:37
 **/
@Configuration
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("开始插入填充");

        this.strictInsertFill(metaObject,"createTimes", Date.class,new Date());
        this.setFieldValByName("isDeleted","1",metaObject);
        log.info("结束插入填充");
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("开始更新填充");
        this.strictInsertFill(metaObject,"isDeleted", LocalDateTime.class,LocalDateTime.now());
        this.strictInsertFill(metaObject,"createTimes", Date.class,new Date());
        log.info("结束更新填充");
    }
}
