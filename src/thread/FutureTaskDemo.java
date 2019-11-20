package thread;

import java.util.concurrent.*;

/**
 * @Author baichen
 * @Date 2019/11/11
 * @Description
 */
public class FutureTaskDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        getResultByExecutors();
        getResultByThread();
    }

    public static void getResultByExecutors() throws ExecutionException, InterruptedException {
        //	创建FutureTask,计算1+2
        FutureTask<Integer> futureTask = new FutureTask<>(() -> 1 + 2);
        //	创建线程池
        ExecutorService es = Executors.newCachedThreadPool();
        //	提交FutureTask
        es.submit(futureTask);
        //	获取计算结果
        Integer result = futureTask.get();
        System.out.println("线程池" + result);
        es.shutdown();
    }

    public static void getResultByThread() throws ExecutionException, InterruptedException {
        //	创建FutureTask,计算1+2
        FutureTask<Integer> futureTask = new FutureTask<>(() -> 3 + 2);
        //	创建并启动线程
        Thread thread = new Thread(futureTask);
        thread.start();
        //	获取计算结果
        Integer result = futureTask.get();
        System.out.println("Thread" + result);
    }
}
