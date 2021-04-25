package com.ginger.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.ginger.mybatisplus.entity.SysUser;
import com.ginger.mybatisplus.service.SysUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: 测试通用service
 * @author: Ginger
 * @create: 2021-04-16 17:46
 **/
@SpringBootTest
public class TestBaseService {

    @Autowired
    private SysUserService sysUserService;
    @Test
    void select(){
        /*
        * 第一类返回值是list<实体类>
                sysUserService.list()
                sysUserService.listByIds(List<String> ids);
                sysUserService.list(wrapper)
                sysUserService.listByMap(map)
        * 第二类返回值是List<Map<String,Object>>
                sysUserService.listMaps();
                sysUserService.listMaps(wrapper);
        * 第三类返回值list<Object>  只会返回每条数据的第一个第一个值
                sysUserService.listObjs();
                sysUserService.listObjs(wrapper);
                sysUserService.listObjs(实现Function函数式接口中的apply接口  返回的每个值 进行函数处理);
                sysUserService.listObjs(wrapper,实现Function函数式接口中的apply接口  返回的每个值 进行函数处理);
        *
        * */
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper();
        /*通过条件查询查询*/
        List<SysUser> list = sysUserService.list(wrapper);
        /*查询全部*/
        List<SysUser> list1 = sysUserService.list();
        /*通过id查询*/
        List<SysUser> sysUsers = sysUserService.listByIds(Arrays.asList("0a4022493d484bf8a951e0f0fd50c23b","7eb870d430848dfd5589209dedf0c93d"));
        /* 通过Map 携带参数  进行条件查询   key 数据库字段 value 值*/
        Map<String,Object> param = new HashMap<>();
        param.put("USER_NAME","小姜");
        wrapper.eq(SysUser::getUserName,"小姜");
        List<SysUser> sysUsers1 = sysUserService.listByMap(param);
        /* 返回值是listMap  */
        List<Map<String, Object>> maps = sysUserService.listMaps();
        List<Map<String, Object>> maps1 = sysUserService.listMaps(wrapper);
        /*返回值是每条数据的第一个字段*/
        List<Object> objects = sysUserService.listObjs();
        List<Long> longs = sysUserService.listObjs(null, a -> {
            return Long.valueOf(a.toString());
        });

        List<String> strings = sysUserService.listObjs(a -> {
            return a.toString();
        });
        System.out.println(strings);

    }

    @Test
    void update(){
        /*

        *   sysUserService.update(wrapper)  通过LambdaUpdateWrapper中的eq设置修改条件  set设置修改的字段和值  只有这个可以对逻辑删除字段进行修改

            sysUserService.update(实体类,wrapper) 通过mapper设置修改条件  根据实体类中字段对应数据库中的字段将符合条件的都修改  如果不穿wrapper将会修改全部数据
        *
            sysUserService.updateById(实体类) 通过实体类中的id 对数据修改

            sysUserService.updateBatchById(List<实体类>) 批量通过id修改

        * */
        LambdaUpdateWrapper<SysUser>  updateWrapper = new LambdaUpdateWrapper();
        LambdaUpdateWrapper<SysUser> result = updateWrapper.eq(SysUser::getUserName, "电厂").set(SysUser::getIsDeleted,"0");
        /* 通过updateWrapper中的条件锁定修改的数据  通过set方法对需要修改的字段赋值  可以对逻辑删除字段进行修改*/
      // boolean update = sysUserService.update(result);
        List<SysUser> sysUsers = sysUserService.listByIds(Arrays.asList("7defce7441ee2b34ff8bfb6a196c161b"));
        /* 通过条件对  符合条件的通过实体类传值的方式对对应字段进行修改   如果不传条件  将会对所有数据修改*/
        //boolean update1 = sysUserService.update(sysUsers.get(0), null);
        /*通过id批量删除*/
        //sysUsers.get(0).setIsDeleted("0");
        //sysUsers.get(0).setUserName("电厂");
        boolean b = sysUserService.updateBatchById(sysUsers);
        //boolean b = sysUserService.updateById(sysUsers.get(0));
        System.out.println();

    }
    @Test
    void delete(){
        /*
            sysUserService.remove(wrapper) 通过mapper设置删除条件

            sysUserService.removeById(String id)   通过id删除

            sysUserService.removeByIds(List<String> ids ) 通过id批量删除

            sysUserService.removeByMap(Map<String,Object> map) 通过map设置删除条件  key = 数据库字段 value= 值
        * */
        LambdaQueryWrapper<SysUser> wrapper  = new LambdaQueryWrapper<>();
        wrapper.eq(SysUser::getId , "a200a9753219c4ca9a77f61282a3e54c");
        //boolean remove = sysUserService.remove(wrapper);
        //System.out.println(remove);
        //boolean aa = sysUserService.removeById("a200a9753219c4ca9a77f61282a3e54c");
        //sysUserService.removeByIds(Arrays.asList("a200a9753219c4ca9a77f61282a3e54c"));
        Map<String,Object> param = new HashMap<>();
        param.put("id","a200a9753219c4ca9a77f61282a3e54c");
        boolean b = sysUserService.removeByMap(param);
        System.out.println(b);
    }
    @Test
    void save(){
        /*
            sysUserService.save(实体类) 对实体类(主键为空)进行添加 主键会通过主键策略自动生成  如果实体类中主键不为空  会报错

            sysUserService.saveBatch(List<实体类>) p批量通过实体类添加  主键会通过主键策略自动生成  如果实体类中主键不为空  会报错

            sysUserService.saveOrUpdate(实体类) 通过实体类中的id判断是否增加或修改 为空新增 不为空 修改

            sysUserService.saveOrUpdate(实体类 ,wrapper) 通过wrapper 进行判断是添加还是修改  如果有符合wrapper条件 就修改  没有就新增  如果wrapper传null 将会把数据全部修改

            sysUserService.saveOrUpdateBatch(List<实体类>)  通过实体类的id  来判断 是新增还是修改
        * */
        LambdaUpdateWrapper<SysUser>  updateWrapper = new LambdaUpdateWrapper();
        List<SysUser> sysUsers = sysUserService.listByIds(Arrays.asList("7defce7441ee2b34ff8bfb6a196c161b"));
        sysUsers.get(0).setUserName("小姜");
        sysUsers.get(0).setId("");
        sysUsers.get(0).setAge(22);
        //boolean save = sysUserService.save(sysUsers.get(0));
        //boolean save = sysUserService.saveBatch(sysUsers);
        boolean save = sysUserService.saveOrUpdate(sysUsers.get(0));
        /*通过wrapper 的条件 来确定是添加还是修改    又符合条件的就修改  没有就新增   如果传null 将会修改全部*/
        updateWrapper.eq(SysUser::getId,"");
        //boolean save = sysUserService.saveOrUpdate(sysUsers.get(0), updateWrapper);
        /* 通过实体类的id  来判断 是新增还是修改 */
        //boolean save = sysUserService.saveOrUpdateBatch(sysUsers);
        System.out.println(save);

    }
}
