package demo42;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Semaphore使用例子
 */
public class SemaphoreTest {

    private static final Semaphore semaphore = new Semaphore(0);

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(() -> {
            System.out.println(Thread.currentThread() + " A task over");
            semaphore.release();
        });
        executorService.submit(() -> {
            System.out.println(Thread.currentThread() + " A task over");
            semaphore.release();
        });
        try {
            semaphore.acquire(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executorService.submit(() -> {
            System.out.println(Thread.currentThread() + " B task over");
            semaphore.release();
        });
        executorService.submit(() -> {
            System.out.println(Thread.currentThread() + " B task over");
            semaphore.release();
        });
        try {
            semaphore.acquire(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Task over");
        executorService.shutdown();
    }
}
