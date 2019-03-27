package demo37;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch使用例子
 */
public class CountDownLatchTest {

    private static final CountDownLatch countDownLatch = new CountDownLatch(2);

    public static void main(String[] args) {

        Thread thread1 = new Thread(() -> {
            try {
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + "over");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                countDownLatch.countDown();
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + "over");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                countDownLatch.countDown();
            }
        });

        thread1.start();
        thread2.start();
        try {
            countDownLatch.await(); // 阻塞
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main thread over!");
    }
}
