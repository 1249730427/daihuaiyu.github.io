package service;

/**
 * 懒汉模式<非线程安全>
 *
 * @author daihuaiyu
 * @create: 2021-01-21 16:47
 **/
public class Singleton  {

    private  static Singleton singleton;

    private Singleton() {
    }

    public static synchronized Singleton getSingleton() {
        if(singleton ==null ){
            singleton = new Singleton();
        }
        return singleton;
    }
}

