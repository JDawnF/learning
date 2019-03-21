package aqs;

import java.util.concurrent.locks.LockSupport;

/**
 * @program: learning
 * @author: baichen
 * 因为 许可默认是被占用的 ，调用park()时获取不到许可，所以进入阻塞状态。
 * 先释放许可，再获取许可，主线程能够正常终止。LockSupport许可的获取和释放，
 * 一般来说是对应的，如果多次unpark，只有一次park也不会出现什么问题，结果是许可处于可用状态。
 **/
public class TestPark {
    public static void main(String[] args) {
//        LockSupport.park();
//        System.out.println("block.");
        Thread thread = Thread.currentThread();
        LockSupport.unpark(thread);//释放许可
        LockSupport.park();// 获取许可
        System.out.println("b");
    }
}
