package demo09;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 简单演示Thread.sleep方法作用
 */
public class SleepTest {

    private static final Lock lock = new ReentrantLock();

    public static void main(String[] args) {

        Thread threadA = new Thread(() -> {
            lock.lock();
            try {
                System.out.println(Thread.currentThread() + "is in sleep");
                Thread.sleep(2000);
                System.out.println(Thread.currentThread() + "is in awaked");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });

        Thread threadB = new Thread(() -> {
            lock.lock();
            try {
                System.out.println(Thread.currentThread() + "is in sleep");
                Thread.sleep(1000);
                System.out.println(Thread.currentThread() + "is in awaked");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });

        threadA.start();
        threadB.start();
    }
}
