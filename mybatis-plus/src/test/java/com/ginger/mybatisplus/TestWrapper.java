package com.ginger.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ginger.mybatisplus.Mapper.SysUserMapper;
import com.ginger.mybatisplus.entity.SysUser;
import com.ginger.mybatisplus.service.SysUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @description: mapper
 * @author: Ginger
 * @create: 2021-04-22 10:15
 **/
@SpringBootTest
public class TestWrapper {
    @Autowired
    private SysUserService userService;

    @Autowired
    private SysUserMapper userMapper;
    @Test
    void lambdaQueryWrapper(){
        /**
         *  eq  相等
         *          eq(实体类字段，值)     根据实体类字段值作为条件查询  字段的值为空也算条件
         *          eq(boolean，实体类字段，值)  根据实体类字段值作为条件查询  boolean=false 自动过滤字段值为空的条件  =true不过滤 效果和上面一样
         *  ne  不等
         *          ne(实体类字段，值)
         *          ne(boolean,数据库字段，值)  boolean true该条件生效 false该条件不起作用  但是如果需求是某字段不等于空  不能用ne  !=""查不出数据  需要用is not null
         *  gt  大于
         *          gt(实体类字段，值)
         *          gt(boolean,数据库字段，值)
         *  ge  大于等于
         *          ge(实体类字段，值)
         *          ge(boolean,数据库字段，值)
         *  lt  小于
         *          lt(实体类字段，值)
         *          lt(boolean，数据库字段，值)
         *  le  小于等于
         *          le(实体类字段，值)
         *          le(boolean，实体类字段，值)
         *  between  在v1 和 v2之间 包括v1和v2
         *          between(数据库字段,)
         *
         */
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        //queryWrapper.eq(false,SysUser::getUserName,"");
        //queryWrapper.ne(SysUser::getUserName,"小姜");
        //queryWrapper.ne(false,SysUser::getUserName,"小姜");
        //queryWrapper.gt(SysUser::getAge,11);
        //queryWrapper.le(SysUser::getAge,11);
        //queryWrapper.lt(SysUser::getAge,"22");
        //queryWrapper.between(SysUser::getAge,"11","22");
        Page<SysUser> page = new Page<>(2,2);
        Page<SysUser> page2 = userMapper.selectPage(page, null);
        List<SysUser> list = page2.getRecords();
        long total = page2.getTotal();
        System.out.println("total"+total+list.toString());
    }

}
