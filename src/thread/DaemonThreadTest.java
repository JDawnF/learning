package thread;

import java.util.concurrent.TimeUnit;

/**
 * @program: learning
 * @author: baichen
 *
 **/
public class DaemonThreadTest {

    public static void main(String[] args) {
        Thread mainThread = new Thread(new Runnable() {
            @Override
            public void run() {
                Thread childThread = new Thread(new ClildThread());
                //childThread设置为守护线程，所以main线程执行完childThread只会输出一次
                childThread.setDaemon(true);
                childThread.start();
                System.out.println("I'm main thread...");
            }
        });
        mainThread.start();
    }
}

class ClildThread implements Runnable {
    @Override
    public void run() {
        while (true) {//没有守护线程就会一直输出
            System.out.println("I'm child thread..");
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

