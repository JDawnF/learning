package thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @program: learning
 * @author: baichen
 * ExecutorService、Callable<Class>、Future 有返回值线程
 * 有返回值的任务必须实现 Callable 接口，类似的，无返回值的任务必须 Runnable 接口。
 * 执行 Callable 任务后，可以获取一个 Future 的对象，
 * 在该对象上调用 get 就可以获取到 Callable 任务 返回的 Object 了，
 * 再结合线程池接口 ExecutorService 就可以实现传说中有返回结果的多线程 了。
 **/
public class ReturnThread implements Callable {
    public ReturnThread(String s) {
        return;
    }

    @Override
    public Object call() throws Exception {
        return null;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int taskSize = 10;
        //创建定长线程池，可以控制线程的最大并发数，超出就在队列等待
        ExecutorService pool = Executors.newFixedThreadPool(taskSize);
        // 创建多个有返回值的任务
        List<Future> list = new ArrayList<Future>();
        for (int i = 0; i < taskSize; i++) {
            Callable c = new ReturnThread(i + " ");
            // 执行任务并获取 Future 对象
            Future f = pool.submit(c);
            list.add(f);
        }
        // 关闭线程池
        pool.shutdown();
        // 获取所有并发任务的运行结果
        for (Future f : list) {
            // 从 Future 对象上获取任务的返回值，并输出到控制台
            System.out.println("res:"
                    + f.get().toString());
        }

        // 基于线程池的方式
        // 创建线程池
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        while (true) {
            threadPool.execute(new Runnable() {
                // 提交多个线程任务，并执行
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + " is running ..");
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}

