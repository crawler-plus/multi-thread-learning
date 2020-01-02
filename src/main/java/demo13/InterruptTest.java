package demo13;

import java.util.concurrent.TimeUnit;

/**
 * 调用子线程的interrupt方法可以中断长时间等待的子线程的执行。
 */
public class InterruptTest {

    public static void main(String[] args) throws Exception {

        Thread threadOne = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(10000);
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread() + "is interrupt while running");
            }
        });

        threadOne.start();
        TimeUnit.SECONDS.sleep(2);
        threadOne.interrupt(); // 在子线程长时间等待情况下，调用子线程的interrupt方法可以提前中断子线程的执行。
        threadOne.join(); // 等待子线程执行完毕
        System.out.println(Thread.currentThread() + "is over");
    }
}
