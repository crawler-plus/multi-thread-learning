package demo46;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * ScheduledThreadPoolExecutor执行时，其中的一个任务在执行中向run方法外抛出异常，其他任务会正常执行！
 */
public class ScheduledThreadPoolExecutorTest {

    private static final ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1);

    public static void main(String[] args) {

        scheduledThreadPoolExecutor.schedule(() -> {
            System.out.println("one task");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            throw new RuntimeException("error!");
        }, 500, TimeUnit.MILLISECONDS);

        scheduledThreadPoolExecutor.schedule(() -> {
            for(int i = 0; i < 2; i ++) {
                System.out.println("two task");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, 1000, TimeUnit.MILLISECONDS);
        scheduledThreadPoolExecutor.shutdown();
    }
}
