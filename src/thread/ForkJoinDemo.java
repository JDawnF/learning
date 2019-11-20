package thread;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @Author baichen
 * @Date 2019/11/11
 * @Description 通过Fork/Join实现计算指定的斐波那契数列
 */
public class ForkJoinDemo {
    public static void main(String[] args) {
        //创建ForkJoin线程池,parallelism默认是cpu核心数，ForkJoinPool里线程数量依据于它，
        // 但不表示最大线程数，不要等同于ThreadPoolExecutor里的corePoolSize或者maximumPoolSize。
        ForkJoinPool fjp = new ForkJoinPool(4);
        //创建分治任务,第6位
        Fibonacci fib = new Fibonacci(38);
        //启动分治任务
        Integer result = fjp.invoke(fib);
        //输出结果
        System.out.println(result);
    }

    //递归任务
    static class Fibonacci extends RecursiveTask<Integer> {
        final int n;

        Fibonacci(int n) {
            this.n = n;
        }

        protected Integer compute() {
            if (n <= 1) return n;
            Fibonacci f1 = new Fibonacci(n - 1);
            //创建子任务
            f1.fork();
            Fibonacci f2 = new Fibonacci(n - 2);
            //等待⼦任务结果，并合并结果
            return f2.compute() + f1.join();
        }
    }
}
