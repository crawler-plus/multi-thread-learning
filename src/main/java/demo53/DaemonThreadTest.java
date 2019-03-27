package demo53;

/**
 * 守护线程例子
 */
public class DaemonThreadTest {

    private static class DaemonT extends Thread {
        @Override
        public void run() {
            while (true) {
                System.out.println("I am alive");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Thread t = new DaemonT();
        t.setDaemon(true); // 设置子线程为守护线程，当主线程停止时，子线程也结束。
        t.start();
        Thread.sleep(2000);
    }
}
