package demo12;

/**
 * 演示如何优雅的中断一个执行的线程。
 */
public class InterruptTest {

    public static void main(String[] args) throws Exception {
        Thread thread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); // 执行当前线程的interrupt方法。
                }
                System.out.println(Thread.currentThread() + "is running");
            }
        });

        thread.start();
        Thread.sleep(2000);
        thread.interrupt(); // 这里设置thread线程的中断信号，在thread线程中判断是否被中断，如果被中断则中断thread线程
        thread.join(); // 等待thread线程执行完毕
        System.out.println(Thread.currentThread() + "is over");
    }
}
