package com.ginger.mybatisplus.entity;


import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @Title: user
 * @Description: java类作用描述
 * @Author: jiangwg
 * @CreateDate: 2020/7/6 16:01
 * @Version: 1.0
 */
@Data
@TableName("SYS_USER")
public class SysUser implements Serializable {
    /**
     * ID
     */
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;
    /**
     * 用户名
     */
    @TableField("USER_NAME")
    private String userName;
    /**
     * 密码
     */
    @TableField("PASS_WORD")
    private String passWord;
    /**
     * 年龄
     */
    @TableField("AGE")
    private int age ;
    /**
     * 头像
     */
    @TableField("HEAD_PORTRAIT")
    private String headPortrait;
    /**
     * 邮箱
     */
    @TableField("email")
    private String email;
    /**
     * 创建时间
     */
    @TableField(value = "CREATE_TIMES",fill = FieldFill.INSERT_UPDATE)
    private Date createTimes;
    /**
     * 逻辑删除 0用户正常使用 1用户删除
     */
    @TableField(value = "IS_DELETED")
    @TableLogic(value = "1",delval = "0")
    private String isDeleted;

    public SysUser() {
    }

    public SysUser(Builder builder){
        this.id = builder.id;
        this.userName = builder.userName;
        this.passWord = builder.passWord;
        this.age = builder.age;
        this.headPortrait = builder.headPortrait;
        this.email = builder.email;
        this.createTimes = builder.createTimes;
        this.isDeleted = builder.isDeleted;
    }

    public static class Builder{
        private String id;
        private String userName;
        private String passWord;
        private int age ;
        private String headPortrait;
        private String email;
        private Date createTimes;
        private String isDeleted;

        public Builder id (String id){
            this.id = id;
            return this;
        }
        public Builder userName (String userName){
            this.userName = userName;
            return this;
        }
        public Builder passWord (String passWord){
            this.passWord = passWord;
            return this;
        }
        public Builder age (int age){
            this.age = age;
            return this;
        }public Builder headPortrait (String headPortrait){
            this.headPortrait = headPortrait;
            return this;
        }
        public Builder email (String email){
            this.email = email;
            return this;
        }
        public Builder createTimes (Date createTimes){
            this.createTimes = createTimes;
            return this;
        }public Builder isDeleted (String isDeleted){
            this.isDeleted = isDeleted;
            return this;
        }
        public SysUser builder(){
            return new SysUser(this);
        }


    }
}
