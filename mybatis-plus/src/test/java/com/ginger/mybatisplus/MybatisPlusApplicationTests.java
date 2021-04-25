package com.ginger.mybatisplus;

import com.ginger.mybatisplus.Mapper.SysUserMapper;
import com.ginger.mybatisplus.entity.SysUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MybatisPlusApplicationTests {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Test
    void contextLoads() {
        SysUser sysUser = new SysUser();
        sysUser.setId("1382944335207809025");
        sysUser.setUserName("222222");
        System.out.println(sysUser);
        sysUserMapper.updateById(sysUser);
    }


}
