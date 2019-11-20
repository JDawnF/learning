package thread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @Author baichen
 * @Date 2019/11/11
 * @Description 串行关系的异步
 */
public class CompletableFutureDemo {
    public static void main(String[] args) {
        getSupplyAsync();
    }

    public static void getSupplyAsync() {
        CompletableFuture<String> f0 = CompletableFuture.supplyAsync(
                () -> "Hello World")              //①
                .thenApply(s -> s + "QQ")        //②依赖1的结果
                .thenApply(String::toUpperCase);//③依赖2的执行结果
        System.out.println(f0.join());
    }

//    public static void getApplyToEither() {
//        CompletableFuture<String> f1 = CompletableFuture.supplyAsync(() -> {
//            int t = getRandom(5, 10);
//            sleep(t, TimeUnit.SECONDS);
//            return String.valueOf(t);
//        });
//        CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> {
//            int t = getRandom(5, 10);
//            sleep(t, TimeUnit.SECONDS);
//            return String.valueOf(t);
//        });
//        CompletableFuture<String> f3 = f1.applyToEither(f2, s -> s);
//        System.out.println(f3.join());
//    }
}
