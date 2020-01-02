package demo61;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/**
 * 重入锁测试
 */
public class ReentrantLockTest implements Runnable {

    private static final Lock lock = new ReentrantLock();

    private static int i = 0;

    @Override
    public void run() {
        IntStream.range(0, 1000000).forEach(ele -> {
            lock.lock();
            lock.lock(); // 锁可以重入
            try {
                i++;
            } finally {
                lock.unlock();
                lock.unlock(); // lock几次就要unlock几次
            }
        });
    }

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = new ReentrantLockTest();
        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(i);
    }
}
