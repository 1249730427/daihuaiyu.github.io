package service;

/**
 * 饿汉模式--内部内部类<线程安全>
 *
 * @author daihuaiyu
 * @create: 2021-01-21 16:59
 **/
public class Singleton2 {

    private static class  instance{
        private  static Singleton2 singleton2 = new Singleton2();
    }

    private Singleton2() {

    }

    public static Singleton2 getInstance() {

        return instance.singleton2;

    }
}

