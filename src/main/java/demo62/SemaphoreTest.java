package demo62;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Semaphore使用例子
 */
public class SemaphoreTest implements Runnable {

    private static final Semaphore semaphore = new Semaphore(5);

    private static final int poolCount = 20;

    @Override
    public void run() {
        try {
            semaphore.acquire();
            Thread.sleep(2000);
            System.out.println(Thread.currentThread() + ": done!");
            semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(poolCount);
        final SemaphoreTest demo = new SemaphoreTest();
        // 每5个一组，执行一次
        for(int i = 0; i < poolCount; i ++) {
            executorService.execute(demo);
        }
        executorService.shutdown();
    }
}
