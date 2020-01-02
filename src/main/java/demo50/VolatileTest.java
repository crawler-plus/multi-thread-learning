package demo50;

/**
 * volatile关键字无法保证原子性
 */
public class VolatileTest {

    private static volatile int i = 0;

    private static class PlusTask implements Runnable {

        @Override
        public void run() {
            for (int k = 0; k < 10000; k++) {
                i++;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[10];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new PlusTask());
            threads[i].start();
        }
        for (int i = 0; i < threads.length; i++) {
            threads[i].join();
        }
        System.out.println(i);
    }
}
