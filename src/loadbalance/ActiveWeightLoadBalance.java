package loadbalance;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import com.weibo.api.motan.cluster.loadbalance.AbstractLoadBalance;
import com.weibo.api.motan.core.extension.SpiMeta;
import com.weibo.api.motan.rpc.Referer;
import com.weibo.api.motan.rpc.Request;

/**
 * @Date: 2019-10-03 21:26
 * @Author: baichen
 * @Description "低并发优化" 负载均衡,最少活跃连接算法
 * 		1） 低并发度优先： referer的某时刻的call数越小优先级越高
 *
 * 		2） 低并发referer获取策略：
 * 				由于Referer List可能很多，比如上百台，如果每次都要从这上百个Referer或者最低并发的几个，性能有些损耗，
 * 				因此 random.nextInt(list.size()) 获取一个起始的index，然后获取最多不超过MAX_REFERER_COUNT的
 * 				状态是isAvailable的referer进行判断activeCount.
 */
@SpiMeta(name = "activeWeight")
public class ActiveWeightLoadBalance<T> extends AbstractLoadBalance<T> {

    @Override
    protected Referer<T> doSelect(Request request) {
        List<Referer<T>> referers = getReferers();

        int refererSize = referers.size();
        int startIndex = ThreadLocalRandom.current().nextInt(refererSize);
        int currentCursor = 0;
        int currentAvailableCursor = 0;

        Referer<T> referer = null;

        while (currentAvailableCursor < MAX_REFERER_COUNT && currentCursor < refererSize) {
            Referer<T> temp = referers.get((startIndex + currentCursor) % refererSize);
            currentCursor++;

            if (!temp.isAvailable()) {
                continue;
            }

            currentAvailableCursor++;

            if (referer == null) {
                referer = temp;
            } else {
                if (compare(referer, temp) > 0) {
                    referer = temp;
                }
            }
        }

        return referer;
    }

    @Override
    protected void doSelectToHolder(Request request, List<Referer<T>> refersHolder) {
        List<Referer<T>> referers = getReferers();

        int refererSize = referers.size();
        int startIndex = ThreadLocalRandom.current().nextInt(refererSize);
        int currentCursor = 0;
        int currentAvailableCursor = 0;

        while (currentAvailableCursor < MAX_REFERER_COUNT && currentCursor < refererSize) {
            Referer<T> temp = referers.get((startIndex + currentCursor) % refererSize);
            currentCursor++;

            if (!temp.isAvailable()) {
                continue;
            }

            currentAvailableCursor++;

            refersHolder.add(temp);
        }

        Collections.sort(refersHolder, new LowActivePriorityComparator<T>());
    }

    private int compare(Referer<T> referer1, Referer<T> referer2) {
        return referer1.activeRefererCount() - referer2.activeRefererCount();
    }

    static class LowActivePriorityComparator<T> implements Comparator<Referer<T>> {
        @Override
        public int compare(Referer<T> referer1, Referer<T> referer2) {
            return referer1.activeRefererCount() - referer2.activeRefererCount();
        }
    }

}
