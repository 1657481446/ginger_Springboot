package com.ginger.mybatisplus.practice.java8;
/**
 * @author 未来
 */
public interface FunctionInterface {

    /**
     * 函数式接口中唯一的接口
     */
    void interfaceMethod();

    /**
     * 函数式接口中可以允许静态非抽象方法
     */
    public static void staticMethod(){
        System.out.println("静态非抽象");
    }

    /**
     * 函数式接口中可以允许default修饰的非抽线方法,该方法在接口的对应实现类中不要求一定实现 可以实现也可以不实现
     */
    default void  defaultMethod(){
        System.out.println("default方法");
    };

    /**
     * 函数值接口可以定义java.lang.Object 下的public 方法 例如 toString()
     * @return 以字符串的形式返回
     */
    @Override
    String toString();

    /**
     *函数值接口可以定义java.lang.Object 下的public 方法 例如 equals
     * @param obj
     * @return 和传入的参数对比
     */
    @Override
    boolean equals(Object obj);
}

class RealizeInterface implements FunctionInterface{

    @Override
    public void interfaceMethod() {
        this.defaultMethod();
    }

    @Override
    public void defaultMethod() {
        System.out.println("111");
    }

    public static void main(String[] args) {
        RealizeInterface realizeInterface = new RealizeInterface();
        realizeInterface.interfaceMethod();
    }
}