package thread.STM;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @Author baichen
 * @Date 2019/11/20
 * @Description 自定义实现STM
 * STM事务实现类
 */
public final class STMTxn implements Txn {

    //事务ID⽣成器
    private static AtomicLong txnSeq = new AtomicLong(0);

    //当前事务所有的相关数据
    private Map<TxnRef, VersionedRef> inTxnMap = new HashMap<>();
    //当前事务所有需要修改的数据
    private Map<TxnRef, Object> writeMap = new HashMap<>();
    //当前事务ID
    private long txnId;

    //构造函数，⾃动⽣成当前事务ID(原子类CAS实现)
    STMTxn() {
        txnId = txnSeq.incrementAndGet();
    }

    //获取当前事务中的数据
    @Override
    public <T> T get(TxnRef<T> ref) {
        //将需要读取的数据，加⼊inTxnMap
        if (!inTxnMap.containsKey(ref)) {
            inTxnMap.put(ref, ref.curRef);
        }
        return (T) inTxnMap.get(ref).value;
    }

    //在当前事务中修改数据
    @Override
    public <T> void set(TxnRef<T> ref, T value) {
        //将需要修改的数据，加⼊inTxnMap
        if (!inTxnMap.containsKey(ref)) {
            inTxnMap.put(ref, ref.curRef);
        }
        writeMap.put(ref, value);
    }

    //提交事务
    boolean commit() {
        synchronized (STM.commitLock) {
            //是否校验通过
            boolean isValid = true;
            //校验所有读过的数据是否发⽣过变化
            for (Map.Entry<TxnRef, VersionedRef> entry : inTxnMap.entrySet()) {
                VersionedRef curRef = entry.getKey().curRef;
                VersionedRef readRef = entry.getValue();
                //通过版本号来验证数据是否发⽣过变化
                if (curRef.version != readRef.version) {
                    isValid = false;
                    break;
                }
            }
            //如果校验通过，则所有更改⽣效
            if (isValid) {
                writeMap.forEach((k, v) -> {
                    k.curRef = new VersionedRef(v, txnId);
                });
            }
            return isValid;
        }
    }
}