package thread;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author baichen
 * @Date 2019/11/14
 * @Description Guava的RateLimiter demo
 * 2个请求/秒指的是每秒最多允许2个请求通过限流器，其实在Guava中，流速还有更深一层的意思：
 * 是一种匀速的概念，2个请求/秒等价于1个请求/500毫秒。
 */
public class GuavaRateLimiter {
    public static void main(String[] args) {
        //限流器流速：2个请求/秒
        RateLimiter limiter = RateLimiter.create(2.0);
        //执⾏任务的线程池
        ExecutorService es = Executors.newFixedThreadPool(1);
        //记录上⼀次执⾏时间
        final long[] prev = {System.nanoTime()};
        //测试执⾏20次
        for (int i = 0; i < 20; i++) {
        //限流器限流
            limiter.acquire();
        //提交任务异步执⾏
            es.execute(() -> {
                long cur = System.nanoTime();
        //打印时间间隔：毫秒
                System.out.println(
                        (cur - prev[0]) / 1000000);    // 在lambda中prev要用数组且用final修饰
                prev[0] = cur;
            });
        }
    }
}
