package thread.STM;

/**
 * @Author baichen
 * @Date 2019/11/20
 * @Description 自定义实现STM
 */
@FunctionalInterface
public interface TxnRunnable {
    void run(Txn txn);
}
