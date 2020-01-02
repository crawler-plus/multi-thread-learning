package demo56;

/**
 * synchronized作用于实例方法上，保证同步
 */
public class AccountingSynchronized2Test implements Runnable {

    private static final AccountingSynchronized2Test instance = new AccountingSynchronized2Test();

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
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(i);
    }
}
