package com.ginger.mybatisplus.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ginger.mybatisplus.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;

/**
 * @description: mapper
 * @author: Ginger
 * @create: 2021-04-15 20:39
 **/
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {
}
