package demo39;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CyclicBarrier使用例子
 */
public class CyclicBarrierTest {

    private static final CyclicBarrier cyclicBarrier = new CyclicBarrier(2, () -> System.out.println(Thread.currentThread() + "task1 merge result"));

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(() -> {
            System.out.println(Thread.currentThread() + "task1-1");
            System.out.println(Thread.currentThread() + "enter in barrier");
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread() + "enter out barrier");
        });

        executorService.submit(() -> {
            System.out.println(Thread.currentThread() + "task1-2");
            System.out.println(Thread.currentThread() + "enter in barrier");
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread() + "enter out barrier");
        });

        executorService.shutdown();

    }
}
