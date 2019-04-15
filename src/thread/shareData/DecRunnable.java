package thread.shareData;

/**
 * @program: learning
 * @author: baichen
 **/
public class DecRunnable implements Runnable {
    MyData data;

    public DecRunnable(MyData data) {
        this.data = data;
    }

    public void run() {
        data.dec();
    }
}
