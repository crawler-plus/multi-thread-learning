package demo04;

import java.util.concurrent.TimeUnit;

/**
 * 演示了两个线程互相持有锁的情况，其中一个线程还占有一个锁的情况下，另一个线程无法获取这把锁。
 */
public class DoubleWaitTest {

    private static final Object resourceA = new Object();

    private static final Object resourceB = new Object();

    public static void main(String[] args) throws Exception {

        Thread threadA = new Thread(() -> {
            // 获取resourceA的锁
            synchronized (resourceA) {
                System.out.println(Thread.currentThread() + "get resourceA lock");
                // 获取resourceB的锁
                synchronized (resourceB) {
                    System.out.println(Thread.currentThread() + "get resourceB lock");
                    try {
                        // 释放resourceA的锁
                        resourceA.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread threadB = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 在线程A释放resourceA的锁之后执行下面的片段
            synchronized (resourceA) {
                System.out.println(Thread.currentThread() + "get resourceA lock");
                // 由于线程A只释放了resourceA的锁，没有释放resourceB的锁，所以下面代码无法执行，程序不能中断
                synchronized (resourceB) {
                    System.out.println(Thread.currentThread() + "get resourceB lock");
                }
            }
        });

        threadA.start();
        threadB.start();
        threadA.join();
        threadB.join();
    }
}
