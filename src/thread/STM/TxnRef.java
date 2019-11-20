package thread.STM;

import static javafx.scene.input.KeyCode.T;

/**
 * @Author baichen
 * @Date 2019/11/20
 * @Description 自定义实现STM
 * TxnRef这个类负责完成事务内的读写操作，读写操作委托给了接口Txn，Txn代表的是读写操作所在的当前事务，
 * 内部持有的curRef代表的是系统中的最新值。
 */
public class TxnRef<T> {
    //当前数据，带版本号
    volatile VersionedRef curRef;

    //构造⽅法
    public TxnRef(T value) {
        this.curRef = new VersionedRef(value, 0L);
    }

    //获取当前事务中的数据
    public T getValue(Txn txn) {
        return txn.get(this);
    }

    //在当前事务中设置数据
    public void setValue(T value, Txn txn) {
        txn.set(this, value);
    }
}
