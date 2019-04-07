package thread;

/**
 * @program: learning
 * @author: baichen
 * 通过继承Thread创建一个线程
 *（1）定义Thread类的子类，并重写该类的run方法，该run方法的方法体就代表了线程要完成的任务。
 * 因此把run()方法称为执行体。
 *（2）创建Thread子类的实例，即创建了线程对象。
 *（3）调用线程对象的start()方法来启动该线程。
 **/
public class FirstThreadTest extends Thread {
    int i = 0;

    //重写run方法，run方法的方法体就是现场执行体
    public void run() {
        for (; i < 100; i++) {
            System.out.println(getName() + "  " + i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            // 输出的是main线程
            System.out.println(Thread.currentThread().getName() + "  : " + i);
            // 当达到20的时候，新建两个线程，输出的是另外两个线程
            if (i == 20) {
                new FirstThreadTest().start();
                new FirstThreadTest().start();
            }
        }
    }
}

