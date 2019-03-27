package demo14;

import java.util.concurrent.TimeUnit;

/**
 * 经典的死锁例子
 */
public class DeadLockTest {

    private static final Object object1 = new Object();

    private static final Object object2 = new Object();

    public static void main(String[] args) {

        Thread thread1 = new Thread(() -> {
            synchronized (object1) {
                System.out.println(Thread.currentThread() + "get object1");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread() + "waiting get object2");
                synchronized (object2) {
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            synchronized (object2) {
                System.out.println(Thread.currentThread() + "get object2");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread() + "waiting get object1");
                synchronized (object1) {
                }
            }
        });

        // 线程1和线程2分别持有对方线程的锁，导致死锁！
        thread1.start();
        thread2.start();
    }
}
