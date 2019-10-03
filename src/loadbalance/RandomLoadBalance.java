package loadbalance;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import com.weibo.api.motan.cluster.loadbalance.AbstractLoadBalance;
import com.weibo.api.motan.core.extension.SpiMeta;
import com.weibo.api.motan.rpc.Referer;
import com.weibo.api.motan.rpc.Request;

/**
 * @Date: 2019-10-03 21:15
 * @Author: baichen
 * @Description 随机算法
 */
@SpiMeta(name = "random")
public class RandomLoadBalance<T> extends AbstractLoadBalance<T> {
    @Override
    protected Referer<T> doSelect(Request request) {
        List<Referer<T>> referers = getReferers();

        int idx = (int) (ThreadLocalRandom.current().nextDouble() * referers.size());
        for (int i = 0; i < referers.size(); i++) {
            Referer<T> ref = referers.get((i + idx) % referers.size());
            if (ref.isAvailable()) {
                return ref;
            }
        }
        return null;
    }

    @Override
    protected void doSelectToHolder(Request request, List<Referer<T>> refersHolder) {
        List<Referer<T>> referers = getReferers();

        int idx = (int) (ThreadLocalRandom.current().nextDouble() * referers.size());
        for (int i = 0; i < referers.size(); i++) {
            Referer<T> referer = referers.get((i + idx) % referers.size());
            if (referer.isAvailable()) {
                refersHolder.add(referer);
            }
        }
    }
}
