package thread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @Author baichen
 * @Date 2019/11/11
 * @Description 模拟MapReduce统计单词数量
 */
public class ForkJoinDemo2 {
    public static void main(String[] args) {
        String[] fc = {"hello world", "hello me", "hello fork", "hello join", "fork join in world"};
        //创建ForkJoin线程池,parallelism默认是cpu核心数，ForkJoinPool里线程数量依据于它，但不表示最大线程数，不要等同于ThreadPoolExecutor里的corePoolSize或者maximumPoolSize。
        ForkJoinPool fjp = new ForkJoinPool(3);
        //创建任务
        MR mr = new MR(fc, 0, fc.length);
        //启动任务
        Map<String, Long> result = fjp.invoke(mr);
        // 输出结果,k为单词，v为出现的次数
        result.forEach((k, v) -> System.out.println(k + ":" + v));
    }

    // MR模拟类
    static class MR extends RecursiveTask<Map<String, Long>> {
        private String[] fc;
        private int start, end;

        /**
         * MR构造函数
         * @param fc 单词数组
         * @param fr 起始位置
         * @param to 末尾位置
         */
        MR(String[] fc, int fr, int to) {
            this.fc = fc;
            this.start = fr;
            this.end = to;
        }

        @Override
        protected Map<String, Long> compute() {
            if (end - start == 1) {
                return calc(fc[start]);
            } else {    // 二分,分治
                int mid = (start + end) / 2;
                MR mr1 = new MR(fc, start, mid);
                mr1.fork();
                MR mr2 = new MR(fc, mid, end);
                //计算⼦任务，并返回合并的结果
                return merge(mr2.compute(), mr1.join());    // 这两者要与上面的fork相对应，先fork再join
            }
        }

        //合并结果
        private Map<String, Long> merge(Map<String, Long> r1, Map<String, Long> r2) {
            Map<String, Long> result = new HashMap<>();
            result.putAll(r1);
            //合并结果
            r2.forEach((k, v) -> {
                Long c = result.get(k);
                if (c != null)
                    result.put(k, c + v);
                else
                    result.put(k, v);
            });
            return result;
        }

        //统计单词数量
        private Map<String, Long> calc(String line) {
            Map<String, Long> result = new HashMap<>();
            //分割单词
            String[] words = line.split("\\s+");
            //统计单词数量
            for (String w : words) {
                Long v = result.get(w); // 单词对应的次数
                if (v != null)
                    result.put(w, v + 1);
                else
                    result.put(w, 1L);
            }
            return result;
        }
    }
}