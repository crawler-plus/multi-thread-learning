package demo51;

/**
 * volatile关键字可以保证可见性和有序性
 */
public class VolatileTest {

    private static volatile boolean ready;

    private static int number;

    public static class ReaderThread extends Thread {

        @Override
        public void run() {
            while (!ready) {
                number = 42;
                break;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        new ReaderThread().start();
        Thread.sleep(1000);
        ready = true;
        System.out.println(number);
        Thread.sleep(10000);
    }
}
