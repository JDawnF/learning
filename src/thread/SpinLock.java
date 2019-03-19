package thread;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @program: learning
 * @author: baichen
 * 自旋锁
 * 使用了CAS原子操作，lock函数将owner设置为当前线程，并且预测原来的值为空。
 * unlock函数将owner设置为null，并且预测值为当前线程。
 *
 * 当有第二个线程调用lock操作时由于owner值不为空，导致循环一直被执行，
 * 直至第一个线程调用unlock函数将owner设置为null，第二个线程才能进入临界区。
 **/
public class SpinLock {

    private AtomicReference<Thread> sign =new AtomicReference<>();

    public void lock(){
        Thread current = Thread.currentThread();
        while(!sign .compareAndSet(null, current)){
        }
    }

    public void unlock (){
        Thread current = Thread.currentThread();
        sign .compareAndSet(current, null);
    }
}
