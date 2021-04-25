package com.ginger.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ginger.mybatisplus.Mapper.SysUserMapper;
import com.ginger.mybatisplus.entity.SysUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: 通用Mapper接口
 * @author: Ginger
 * @create: 2021-04-16 15:12
 **/
@SpringBootTest
public class TestBaseMapper {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Test
    public void insert(){
        SysUser sysUser = new SysUser.Builder().userName("小姜").age(21).passWord("11").builder();
        System.out.println(sysUser);
        sysUserMapper.insert(sysUser);
    }
    @Test
    public void select(){
        /* 通过条件构造器wrapper 作为条件查询*/
        List<SysUser> sysUsers = sysUserMapper.selectList(null);
        /*通过id 查询*/
        SysUser sysUser = sysUserMapper.selectById("0a4022493d484bf8a951e0f0fd50c23b");
        /*通过多个id 批量查询*/
        List<SysUser> sysUsers1 = sysUserMapper.selectBatchIds(Arrays.asList("0a4022493d484bf8a951e0f0fd50c23b","7defce7441ee2b34ff8bfb6a196c161b"));
        /*通过条件构造器wrapper 作为条件查询查询条数*/
        Integer integer = sysUserMapper.selectCount(null);
        Map<String,Object> map = new HashMap<>();
        /*通过Map传递传参 key 数据库中字段名称 value 对应的值*/
        map.put("USER_NAME","小姜");
        List<SysUser> sysUsers2 = sysUserMapper.selectByMap(map);
        /*通过条件构造器wrapper 作为条件查询  返回值是list<Map>    key是数据库字段(不是实体类)  value 对应的值*/
        List<Map<String, Object>> maps = sysUserMapper.selectMaps(null);
        /*通过条件构造器作为条件查询  返回值是查询的第一个字段*/
        List<Object> objects = sysUserMapper.selectObjs(null);
        /*通过条件构造器作为条件查询一条记录  如果根据条件查询出来多条会抛出异常*/
        SysUser sysUser1 = sysUserMapper.selectOne(null);
    }
    @Test
    public void update(){
        /*
        * 第一种直接直接通过id修改， 第二种通过wrapper条件修改 如果不穿条件 将会修改全部数据除了主键id
        *
        * 两个方法返回值都是根据条件匹配出来的个数 而不是实际修改的个数  这两种都不能对逻辑删除字段修改
        * */
        SysUser sysUser  = new SysUser.Builder().id("7defce7441ee2b34ff8bfb6a196c161b").headPortrait("head").email("qq").builder();
        sysUser.setIsDeleted("0");
        //int i = sysUserMapper.updateById(sysUser);
        System.out.println(sysUser);
        int update = sysUserMapper.update(sysUser, null);
    }
    @Test
    public void delete(){
        /* 根据wrapper的条件删除数据   如果为null 会全部删除    返回值是指的删除的数据个数*/
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUser::getUserName,"插入");
        int delete1 = sysUserMapper.delete(wrapper);
        /* 根据主键id 删除*/
        int i = sysUserMapper.deleteById("7defce7441ee2b34ff8bfb6a196c161b");
        /* 根据主键id批量删除*/
        int delete = sysUserMapper.deleteBatchIds(Arrays.asList("7defce7441ee2b34ff8bfb6a196c161b", "a200a9753219c4ca9a77f61282a3e54c"));
        /*通过Map传递传参 key 数据库中字段名称 value 对应的值*/
        Map<String,Object> map = new HashMap<>();
        map.put("USER_NAME","小姜");
        int i2 = sysUserMapper.deleteByMap(map);
    }
}
