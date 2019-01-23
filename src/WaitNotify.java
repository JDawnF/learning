/**
 * @program: learning
 * @description: wait会释放锁
 * @author: baichen
 * @create: 2019-01-23 11:11
 **/
public class WaitNotify {
    public static void main(String[]args)throws Exception {
        final Object obj = new Object();
        Thread t1 = new Thread() {
            public void run() {
                synchronized (obj) {    //同步锁
                    try {
                        obj.wait(); //wait会释放锁，然后t2获得锁
                        System.out.println("Thread 1 wake up.");
                    } catch (InterruptedException e) {
                    }
                }
            }
        };
        t1.start();
        Thread.sleep(1000);//We assume thread 1 must start up within 1 sec.
        Thread t2 = new Thread() {
            public void run() {
                synchronized (obj) {    //同步锁，获得t1的wait方法释放的锁
                    obj.notifyAll();    //唤醒t1，但是还没释放锁，要执行完才释放锁
                    System.out.println("Thread 2 sent notify.");
                }
            }
        };
        t2.start();
    }
}
