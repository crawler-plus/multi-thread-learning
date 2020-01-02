package demo49;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池使用FutureTask时，如果把拒绝策略设置为DiscardPolicy或者DiscardOldestPolicy
 * 并且在被拒绝任务的Future对象上调用了无参的get方法，那么调用线程会一直被阻塞。
 */
public class ThreadPoolFutureTaskBlockTest {

    private static final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1, 1L, TimeUnit.MINUTES,
            new ArrayBlockingQueue<>(1), new ThreadPoolExecutor.DiscardPolicy());

    public static void main(String[] args) throws Exception {

        Future<Integer> future1 = threadPoolExecutor.submit(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1;
        });

        Future<Integer> future2 = threadPoolExecutor.submit(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 2;
        });

        Future<Integer> future3 = null;
        try {
            future3 = threadPoolExecutor.submit(() -> {
                try {
                    Thread.sleep(6000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return 7;
            });
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        System.out.println("task 1:" + future1.get());
        System.out.println("task 2:" + future2.get());
        System.out.println("task 3:" + future3.get()); // 会一直卡死在这里
        threadPoolExecutor.shutdown();
    }
}
