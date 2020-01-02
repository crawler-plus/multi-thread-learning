package demo54;

/**
 * 线程优先级测试
 */
public class ThreadPriorityTest {

    private static class HighPriority extends Thread {

        static int count = 0;

        @Override
        public void run() {
            while (true) {
                synchronized (ThreadPriorityTest.class) {
                    count++;
                    if (count > 10000000) {
                        System.out.println("High priority thread is complete");
                        break;
                    }
                }
            }
        }
    }

    private static class LowPriority extends Thread {

        static int count = 0;

        @Override
        public void run() {
            while (true) {
                synchronized (ThreadPriorityTest.class) {
                    count++;
                    if (count > 10000000) {
                        System.out.println("Low priority thread is complete");
                        break;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Thread highPriorityThread = new HighPriority();
        Thread lowPriorityThread = new LowPriority();
        // 设置完线程优先级以后，高优先级的线程会比低优先级的线程优先级更大，但是不保证一直会如此。
        highPriorityThread.setPriority(Thread.MAX_PRIORITY);
        lowPriorityThread.setPriority(Thread.MIN_PRIORITY);
        highPriorityThread.start();
        lowPriorityThread.start();
    }
}
