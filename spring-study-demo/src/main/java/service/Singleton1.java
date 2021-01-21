package service;

/**
 * 饿汉模式<线程安全>
 *
 * @author daihuaiyu
 * @create: 2021-01-21 16:52
 **/
public class Singleton1 {

    private  static  Singleton1 singleton1 = new Singleton1();

    private Singleton1() {
    }

    public Singleton1 getSingleton1() {
        return singleton1;
    }
}

