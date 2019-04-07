package thread;

/**
 * @program: learning
 * @author: baichen
 * 通过Runnable接口创建线程类
 * （1）定义runnable接口的实现类，并重写该接口的run()方法，该run()方法的方法体同样是该线程的线程执行体。
 * （2）创建 Runnable实现类的实例，并依此实例作为Thread的target来创建Thread对象，
 * 该Thread对象才是真正的线程对象。
 * （3）调用线程对象的start()方法来启动该线程。
 **/
public class RunnableThreadTest implements Runnable {

    private int i;

    public void run() {
        for (i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
            if (i == 20) {
                // 先创建一个Runnable实现类实例对象，再创建线程，把实例对象作为参数传进去
                RunnableThreadTest rtt = new RunnableThreadTest();
                new Thread(rtt, "新线程1").start();
                new Thread(rtt, "新线程2").start();
            }
        }
    }
}

