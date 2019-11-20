package thread;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedAbstractActor;

/**
 * @Author baichen
 * @Date 2019/11/20
 * @Description Akka(实现了Actor模型的第三方工具) demo
 */
public class ActorDemo {
    //该Actor当收到消息message后，
    //会打印Hello	message
    static class HelloActor extends UntypedAbstractActor {
        @Override
        public void onReceive(Object message) {
            System.out.println("Hello	" + message);
        }
    }

    public static void main(String[] args) {
        //创建Actor系统(Actor不能脱离ActorSystem存在)
        ActorSystem system = ActorSystem.create("HelloSystem");
        //创建HelloActor,调用 system.actorOf方法
        ActorRef helloActor = system.actorOf(Props.create(HelloActor.class));
        //发送消息("Actor")给HelloActor
        helloActor.tell("Actor", ActorRef.noSender());
    }
}
