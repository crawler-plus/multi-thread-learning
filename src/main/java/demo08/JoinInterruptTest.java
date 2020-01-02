package demo08;

import java.util.concurrent.TimeUnit;

/**
 * 在一个线程中调用被阻塞线程的interrupt方法的时候，被阻塞的线程会抛出异常！
 */
public class JoinInterruptTest {

    public static void main(String[] args) {

        final Thread mainThread = Thread.currentThread();

        Thread threadOne = new Thread(() -> {
            System.out.println(Thread.currentThread() + "begin!");
            for (; ; ) {
            }
        });

        Thread threadTwo = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mainThread.interrupt();
        });

        threadOne.start();
        threadTwo.start();

        try {
            // 主线程调用线程A的join方法会被阻塞，当线程B调用主线程的interrupt方法中断主线程的时候，主线程会抛出异常！
            threadOne.join();
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread() + ":" + e);
        }
    }
}
