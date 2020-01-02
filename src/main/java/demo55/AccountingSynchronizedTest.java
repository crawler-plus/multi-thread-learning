package demo55;

/**
 * synchronized作用于代码块上，保证同步
 */
public class AccountingSynchronizedTest implements Runnable {

    private static final AccountingSynchronizedTest instance = new AccountingSynchronizedTest();

    private static int i = 0;

    private void increase() {
        i++;
    }

    @Override
    public void run() {
        synchronized (instance) {
            for (int i = 0; i < 10000; i++) {
                increase();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(i);
    }
}
