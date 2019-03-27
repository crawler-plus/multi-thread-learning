package demo20;

/**
 * volatile关键字禁止指令重排序问题
 */
public class VolatileForbidMemoryReOrderingTest {

    private static volatile boolean ready;

    private static int number;

    private static class ReaderThread extends Thread {

        @Override
        public void run() {
            while (!ready) {
                System.out.println("number=" + number);
            }
            System.out.println("number=" + number);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new ReaderThread().start();
        Thread.sleep(100);
        number = 42;
        ready = true;
        Thread.sleep(2000);
        System.out.println(Thread.currentThread() + "over!");
    }
}
