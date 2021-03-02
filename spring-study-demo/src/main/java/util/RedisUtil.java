package util;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Redis操作工具类
 *
 * @author daihuaiyu
 * @create: 2021-03-02 13:36
 **/
public class RedisUtil {

    private ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);

    private AtomicInteger stock = new AtomicInteger(0);

    public RedisUtil() {
        scheduledExecutorService.scheduleAtFixedRate(() -> stock.addAndGet(1),
                0,
                1,
                TimeUnit.SECONDS);
    }

    public int getStockUsed() {
        return stock.get();
    }
}

