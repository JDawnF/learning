package thread;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedAbstractActor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author baichen
 * @Date 2019/11/20
 * @Description 使用Akka实现计数器，Akka是单线程的，所以不存在竞态条件
 * 启动了4个线程来执行累加操作。整个程序没有锁，也没有CAS，但是程序是线程安全的。
 */
public class CounterActor extends UntypedAbstractActor {
    private int counter = 0;

    @Override
    public void onReceive(Object message) throws Throwable {
        //如果接收到的消息是数字类型，执⾏累加操作，
        //否则打印counter的值
        if (message instanceof Number) {
            counter += ((Number) message).intValue();
        } else {
            System.out.println(counter);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        //创建Actor系统
        ActorSystem system = ActorSystem.create("HelloSystem");
        //4个线程⽣产消息
        ExecutorService es = Executors.newFixedThreadPool(4);
        //创建CounterActor
        ActorRef counterActor = system.actorOf(Props.create(CounterActor.class));
        //⽣产4*100000个消息
        for (int i = 0; i < 4; i++) {
            es.execute(() -> {
                for (int j = 0; j < 100000; j++) {
                    counterActor.tell(1, ActorRef.noSender());
                }
            });
        }
        //关闭线程池
        es.shutdown();
        //等待CounterActor处理完所有消息
        Thread.sleep(1000);
        //打印结果
        counterActor.tell("", ActorRef.noSender());
        //关闭Actor系统
        system.stop(counterActor);
    }
}
