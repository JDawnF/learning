package thread;

import java.util.concurrent.TimeUnit;

import static java.lang.Math.max;
import static java.lang.Math.min;

/**
 * @Author baichen
 * @Date 2019/11/16
 * @Description Guava实现令牌桶算法, 记录并动态计算下一令牌发放的时间
 * 这里假设设令牌桶的容量为b>1，限流速率r=1个请求/秒
 * 逻辑与上面那个差不多，按照令牌桶算法，令牌要首先从令牌桶中出，所以计算令牌桶中的数量，
 * 当有线程请求令牌时，先从令牌桶中出。
 */
public class SimpleLimiterTwo {
    //下⼀令牌产⽣时间
    long next = System.nanoTime();
    //令牌桶的容量
    long maxPermits = 3;
    //发放令牌间隔：纳秒
    long interval = 1000_000_000;

    long storedPermits;

    //请求时间在下⼀令牌产⽣时间之后,则
    //	1.重新计算令牌桶中的令牌数
    //	2.将下⼀个令牌发放时间重置为当前时间
    void resync(long now) {
        if (now > next) {
    //新产⽣的令牌数
            long newPermits = (now - next) / interval;
    //新令牌增加到令牌桶
            storedPermits = min(maxPermits, storedPermits + newPermits);
    //将下⼀个令牌发放时间重置为当前时间
            next = now;
        }
    }

    //预占令牌，返回能够获取令牌的时间
    synchronized long reserve(long now) {
        resync(now);
    //能够获取令牌的时间
        long at = next;
    //令牌桶中能提供的令牌
        long fb = min(1, storedPermits);
    //令牌净需求：⾸先减掉令牌桶中的令牌
        long nr = 1 - fb;
    //重新计算下⼀令牌产⽣时间
        next = next + nr * interval;
    //重新计算令牌桶中的令牌
        this.storedPermits -= fb;
        return at;
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
                TimeUnit.NANOSECONDS.sleep(waitTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}