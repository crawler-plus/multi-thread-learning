package demo63;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁和可重入锁的性能PK
 */
public class ReadWriteLockVSReentrantLockTest {

    private static final Lock lock = new ReentrantLock();

    private static final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    private static final Lock readLock = readWriteLock.readLock();

    private static final Lock writeLock = readWriteLock.writeLock();

    private static int value;

    public Object handleRead(Lock lock) throws Exception {
        try {
            lock.lock();
            Thread.sleep(1000);
            return value;
        } finally {
            lock.unlock();
        }
    }

    public void handleWrite(Lock lock, int index) throws Exception {
        try {
            lock.lock();
            Thread.sleep(1000);
            value = index;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        final ReadWriteLockVSReentrantLockTest demo = new ReadWriteLockVSReentrantLockTest();
        Runnable runnableRead = () -> {
            try {
                demo.handleRead(readLock); // 采用readLock比lock对于读性能更好。因为读读共享，写写互斥，读写互斥。
//                demo.handleRead(lock);
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
        Runnable runnableWrite = () -> {
            try {
                demo.handleWrite(writeLock, new Random().nextInt());
//                demo.handleWrite(lock, new Random().nextInt());
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
        for (int i = 0; i < 18; i++) {
            new Thread(runnableRead).start();
        }
        for (int i = 0; i < 2; i++) {
            new Thread(runnableWrite).start();
        }
    }
}
