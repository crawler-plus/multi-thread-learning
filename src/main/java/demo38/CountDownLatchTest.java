package demo38;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CountDownLatch使用例子（线程池）
 */
public class CountDownLatchTest {

    private static final CountDownLatch countDownLatch = new CountDownLatch(2);

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        executorService.execute(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                countDownLatch.countDown();
            }
        });

        executorService.execute(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                countDownLatch.countDown();
            }
        });

        try {
            countDownLatch.await(); // 阻塞
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("all child thread over");
        executorService.shutdownNow();
    }
}
