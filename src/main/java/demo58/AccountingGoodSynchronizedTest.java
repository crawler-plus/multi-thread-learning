package demo58;

/**
 * synchronized类锁同步成功（锁静态方法）
 */
public class AccountingGoodSynchronizedTest implements Runnable {

    private static int i = 0;

    private static synchronized void increase() {
        i++;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            increase();
        }
    }

    public static void main(String[] args) throws Exception {
        Thread t1 = new Thread(new AccountingGoodSynchronizedTest());
        Thread t2 = new Thread(new AccountingGoodSynchronizedTest());
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(i);
    }
}
