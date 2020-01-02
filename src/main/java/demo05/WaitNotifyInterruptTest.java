package demo05;

/**
 * 其他线程调用一个正在等待的线程的interrupt方法时，另一个线程会抛出异常。
 */
public class WaitNotifyInterruptTest {

    private static final Object object = new Object();

    public static void main(String[] args) throws Exception {
        Thread threadA = new Thread(() -> {
            System.out.println("begin...");
            synchronized (object) {
                try {
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("end...");
            }
        });

        threadA.start();
        Thread.sleep(2000);
        // 当其他线程调用某一个线程的interrupt方法的时候，另一个线程会返回，但是会抛出java.lang.InterruptedException
        threadA.interrupt();
    }
}
