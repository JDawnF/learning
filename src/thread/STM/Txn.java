package thread.STM;

/**
 * @Author baichen
 * @Date 2019/11/20
 * @Description 自定义实现STM
 * 事务入口
 */
public interface Txn {
    <T> T get(TxnRef<T> ref);

    <T> void set(TxnRef<T> ref, T value);
}
