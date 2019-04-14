package thread;

/**
 * @program: learning
 * @author: baichen
 **/
public class NewAThread extends Thread {
    @Override
    public void run() {
        System.out.println("重写run方法");
    }

    static class MyThread implements Runnable{

        @Override
        public void run() {
            System.out.println("MyThread.run()");
        }
    }
    public static void main(String[] args) {
        // 1.继承Thread类创建一个线程
        NewAThread thread1=new NewAThread();
        thread1.start();
        // 2.实现一个Runnable创建一个线程
        //启动 MyThread，需要首先实例化一个 Thread，并传入自己的 MyThread 实例:
        MyThread myThread = new MyThread();
        Thread thread = new Thread(myThread);
        thread.start();
        //事实上，当传入一个 Runnable target 参数给 Thread 后，Thread 的 run()方法就会调用
//        target.run();
//        public void run() {
//            if (target != null) {
//            }
//            target.run();
//        }
    }
}
