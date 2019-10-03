package loadbalance;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import com.weibo.api.motan.cluster.loadbalance.AbstractLoadBalance;
import com.weibo.api.motan.core.extension.SpiMeta;
import com.weibo.api.motan.rpc.Referer;
import com.weibo.api.motan.rpc.Request;
import com.weibo.api.motan.util.MathUtil;

import static com.weibo.api.motan.cluster.loadbalance.AbstractLoadBalance.MAX_REFERER_COUNT;

/**
 * @Date: 2019-10-03 21:18
 * @Author: baichen
 * @Description 轮询算法
 */
@SpiMeta(name = "roundrobin")
public class RoundRobinLoadBalance<T> extends AbstractLoadBalance<T> {

    private AtomicInteger idx = new AtomicInteger(0);

    @Override
    protected Referer<T> doSelect(Request request) {
        List<Referer<T>> referers = getReferers();

        int index = getNextNonNegative();
        for (int i = 0; i < referers.size(); i++) {
            Referer<T> ref = referers.get((i + index) % referers.size());
            if (ref.isAvailable()) {
                return ref;
            }
        }
        return null;
    }

    @Override
    protected void doSelectToHolder(Request request, List<Referer<T>> refersHolder) {
        List<Referer<T>> referers = getReferers();

        int index = getNextNonNegative();
        for (int i = 0, count = 0; i < referers.size() && count < MAX_REFERER_COUNT; i++) {
            Referer<T> referer = referers.get((i + index) % referers.size());
            if (referer.isAvailable()) {
                refersHolder.add(referer);
                count++;
            }
        }
    }

    // get non-negative int
    private int getNextNonNegative() {
//        return MathUtil.getNonNegative(idx.incrementAndGet());    //
        return MathUtil.getPositive(idx.incrementAndGet());  // 错误，只是为了不报错暂时改成这个方法
    }
}
