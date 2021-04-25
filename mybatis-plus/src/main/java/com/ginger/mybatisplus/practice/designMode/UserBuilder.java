package com.ginger.mybatisplus.practice.designMode;

/**
 * @description: 建造者模式
 * @author: Ginger
 * @create: 2021-04-15 16:53
 **/
public class UserBuilder {
    private String id;
    private String username;
    private String password;
    private int age;
    private String email;

    /*
    * 通常我们给对象赋值的话有两种方式：第一种提供公开的set方法对每个字段赋值  第二种 通过不同的构造器来生成对象并赋值
    *   坏处：一旦属性过多 就会造成过多重载的构造方法，或者是繁琐的多次set方法
    *
    *
    * 建造者模式：
    *   在实体类对象中创建一个静态内部类Builder,我们需要的实体类对象由这个Builder来创建。Builder拥有实体类中所有的属性、
    *   无参构造、给各个属性赋值并且返回值是Builder的方法、返回值是实体类的方法。
    *   创建对象的流程：
    *       通过实体类对象或得Builder对象 然后连续依次调用需要赋值的字段对应的方法，最后通过builder()返回赋值号的实体类对象
    *       UserBuilder userBuilder = new UserBuilder.Builder().id("1").username("2").builder();
    *
    *   优点：降低耦合，链式调用 逻辑清晰，赋值对象灵活大大提高
    *   缺点：前期需要做很多的代码工作 每次都需要构建Builder对象
    * */

    public UserBuilder (Builder builder){
        this.id = builder.id;
        this.username = builder.username;
        this.password = builder.password;
        this.age = builder.age;
        this.email = builder.email;
    }

    public static class Builder{
        private String id;
        private String username;
        private String password;
        private int age;
        private String email;

        public Builder id(String id ){
            this.id = id;
            return this;
        };
        public Builder username(String username ){
            this.username = username;
            return this;
        };
        public Builder password(String password ){
            this.password = password;
            return this;
        };
        public Builder age(int age ){
            this.age = age;
            return this;
        };
        public Builder email(String email ){
            this.email = email;
            return this;
        };
        public UserBuilder builder(){
            return new UserBuilder(this);
        }
    }

    public static void main(String[] args) {
        UserBuilder userBuilder = new UserBuilder.Builder().id("1").username("2").builder();
    }
}