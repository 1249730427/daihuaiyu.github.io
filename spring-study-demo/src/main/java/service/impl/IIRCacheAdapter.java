package service.impl;

import service.ICacheAdapter;
import util.IIR;

import java.util.concurrent.TimeUnit;

/**
 * @author :daihuaiyu
 * @Description:
 * @create 2021/2/28 9:36
 */
public class IIRCacheAdapter implements ICacheAdapter {

    private IIR iir = new IIR();

    @Override
    public String get(String key) {
        return iir.gain(key);
    }

    @Override
    public void set(String key, String value) {
        iir.set(key,value);
    }

    @Override
    public void set(String key, String value, long timeout, TimeUnit timeUnit) {
        iir.setExpire(key,value,timeout,timeUnit);
    }

    @Override
    public void del(String key) {
        iir.del(key);
    }
}
