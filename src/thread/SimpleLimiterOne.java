package thread;

import java.util.concurrent.TimeUnit;

import static java.lang.Math.max;

/**
 * @Author baichen
 * @Date 2019/11/16
 * @Description Guava实现令牌桶算法,记录并动态计算下一令牌发放的时间
 * 这里假设设令牌桶的容量为b=1，限流速率r=1个请求/秒
 * reserve()方法，这个方法会为请求令牌的线程预分配令牌，同时返回该线程能够获取令牌的时间。
 * 其实现逻辑就是：如果线程请求令牌的时间在下一令牌产生时间之后，那么该线程立刻就能够获取令牌；
 * 反之，如果请求时间在下一令牌产生时间之前，那么该线程是在下一令牌产生的时间获取令牌。
 * 由于此时下一令牌已经被该线程预占，所以下一令牌产生的时间需要加上1秒。
 */
public class SimpleLimiterOne {
    //下⼀令牌产⽣时间
    long next = System.nanoTime();
    //发放令牌间隔：纳秒
    long interval = 1000_000_000;

    //预占令牌，返回能够获取令牌的时间
    synchronized long reserve(long now) {
        //请求时间在下⼀令牌产⽣时间之后
        //重新计算下⼀令牌产⽣时间
        if (now > next) {
            //将下⼀令牌产⽣时间重置为当前时间
            next = now;
        }
        //能够获取令牌的时间
        long at = next;
        //设置下⼀令牌产⽣时间
        next += interval;
        //返回线程需要等待的时间
        return max(at, 0L);
    }

    //申请令牌
    void acquire() {
        //申请令牌时的时间
        long now = System.nanoTime();
        //预占令牌
        long at = reserve(now);
        long waitTime = max(at - now, 0);
        //按照条件等待
        if (waitTime > 0) {
            try {
                TimeUnit.NANOSECONDS
                        .sleep(waitTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}