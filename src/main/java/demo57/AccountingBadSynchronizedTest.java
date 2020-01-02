package demo57;

/**
 * synchronized对象锁同步失败例子
 */
public class AccountingBadSynchronizedTest implements Runnable {

    private static int i = 0;

    private synchronized void increase() {
        i++;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            increase();
        }
    }

    public static void main(String[] args) throws Exception {
        // 如下两行Thread都指向了不同的Runnable接口的实例，所以使用对象锁同步失败。
        Thread t1 = new Thread(new AccountingBadSynchronizedTest());
        Thread t2 = new Thread(new AccountingBadSynchronizedTest());
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(i);
    }
}
