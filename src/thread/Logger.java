package thread;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.*;

/**
 * @Author baichen
 * @Date 2019/11/13
 * @Description 生产-消费者模式,分阶段提交
 */
public class Logger {
    //任务队列
    final BlockingQueue<LogMsg> bq = new LinkedBlockingQueue<>();
    //flush批量
    static final int batchSize = 500;
    //只需要⼀个线程写⽇志
    ExecutorService es = Executors.newFixedThreadPool(1);

    //启动写⽇志线程
    void start() throws IOException {
        File file = File.createTempFile("foo", ".log");
        final FileWriter writer = new FileWriter(file);
        this.es.execute(() -> {
            try {
                //未刷盘⽇志数量
                int curIdx = 0;
                long preFT = System.currentTimeMillis();
                while (true) {
                    LogMsg log = bq.poll(5, TimeUnit.SECONDS);
                    //写⽇志
                    if (log != null) {
                        writer.write(log.toString());
                        ++curIdx;
                    }
                    //如果不存在未刷盘数据，则⽆需刷盘
                    if (curIdx <= 0) {
                        continue;
                    }//根据规则刷盘
                    if (log != null && log.level == LEVEL.ERROR || curIdx == batchSize || System.currentTimeMillis() - preFT > 5000) {
                        writer.flush();
                        curIdx = 0;
                        preFT = System.currentTimeMillis();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    writer.flush();
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    //写INFO级别⽇志
    void info(String msg) throws InterruptedException {
        bq.put(new LogMsg(LEVEL.INFO, msg));
    }

    //写ERROR级别⽇志
    void error(String msg) throws InterruptedException {
        bq.put(new LogMsg(LEVEL.ERROR, msg));
    }

    //⽇志级别
    enum LEVEL {INFO, ERROR}

    class LogMsg {
        LEVEL level;
        String msg;

        //省略构造函数实现
        LogMsg(LEVEL lvl, String msg) {
        }

        //省略toString()实现
        public String toString() {
            return null;
        }
    }
}
