package com.offer;

public class Singleton {

    /**
     * 饿汉式单例：线程安全
     */
    private static Singleton singleton = new Singleton();

    private Singleton() {
    }

    public static Singleton getInstance() {
        return singleton;
    }

}

/**
 * 懒汉式单例：线程不安全(可以使用synchronized关键字解决，但是多线程环境下效率会变慢，代码如Singleton05)
 */

class Singleton01 {
    private static Singleton01 singleton01 = null;

    private Singleton01() {
    }

    public static Singleton01 getInstance() {
        if (singleton01 == null) {
            singleton01 = new Singleton01();
        }
        return singleton01;
    }

}

/**
 * 单例模式的最佳实践：线程安全，推荐使用
 * <p>
 * 双重空检验 + 中间代码的synchronized代码块
 */
class Singleton02 {

    //volatile:禁止指令重排
    private static volatile Singleton02 singleton02 = null;

    private Singleton02() {

    }

    public static Singleton02 getInstance() {

        if (singleton02 == null) {
            synchronized (Singleton02.class) {
                if (singleton02 == null) {
                    singleton02 = new Singleton02();
                }
            }
        }

        return singleton02;
    }
}

/**
 * 静态代码块的懒汉式单例：线程安全
 */
class Singleton03 {

    private static Singleton03 singleton03 = null;

    static {
        singleton03 = new Singleton03();
    }

    private Singleton03() {

    }

    public static Singleton03 getInstance() {
        return singleton03;
    }

}


/**
 * 静态内部类的单例：线程安全
 */
class Singleton04 {
    private final static class SingletonHolder {
        private final static Singleton04 singleton04 = new Singleton04();
    }

    private Singleton04() {

    }

    public static Singleton04 getInstance() {
        return SingletonHolder.singleton04;
    }
}


class Singleton05 {
    private static Singleton05 singleton05 = null;

    private Singleton05() {
    }

    public static Singleton05 getInstance() {
        if (singleton05 == null) {
            singleton05 = new Singleton05();
        }

        return singleton05;
    }
}


/**
 * 单个实例的枚举，线程安全，是最合适的实现方式，但是推荐使用 双重空检验 的方式
 */
enum Singleton06 {

//    INSTANCE("singleton instance");
//
//    private String name;
//
//    Singleton06(String name) {
//        this.name = name;
//    }

    INSTANCE;

}