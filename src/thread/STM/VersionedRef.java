package thread.STM;

/**
 * @Author baichen
 * @Date 2019/11/20
 * @Description 自定义实现STM
 * VersionedRef这个类的作用就是将对象value包装成带版本号的对象。
 */
public final class VersionedRef<T> {
    final T value;
    final long version;

    //构造⽅法
    public VersionedRef(T value, long version) {
        this.value = value;
        this.version = version;
    }
}
