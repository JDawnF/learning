package thread.shareData;

/**
 * @program: learning
 * @author: baichen
 **/
public class MyData {
    private int j = 0;

    public synchronized void add() {
        j++;
        System.out.println("线程" + Thread.currentThread().getName() + "j 为:" + j);
    }

    public synchronized void dec() {
        j--;
        System.out.println("线程" + Thread.currentThread().getName() + "j 为:" + j);
    }
}
