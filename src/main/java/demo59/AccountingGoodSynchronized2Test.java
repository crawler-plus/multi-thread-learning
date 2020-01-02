package demo59;

import demo55.AccountingSynchronizedTest;

/**
 * synchronized类锁同步成功（锁类的class对象，也可以成功）
 */
public class AccountingGoodSynchronized2Test implements Runnable {

    private static int i = 0;

    private static void increase() {
        i++;
    }

    @Override
    public void run() {
        synchronized (AccountingSynchronizedTest.class) {
            for (int i = 0; i < 10000; i++) {
                increase();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Thread t1 = new Thread(new AccountingGoodSynchronized2Test());
        Thread t2 = new Thread(new AccountingGoodSynchronized2Test());
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(i);
    }
}
