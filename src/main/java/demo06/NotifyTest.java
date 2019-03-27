package demo06;

import java.util.concurrent.TimeUnit;

/**
 * 演示notify和notifyAll的作用。
 */
public class NotifyTest {

    private static final Object obj = new Object();

    public static void main(String[] args) throws Exception {
        Thread threadA = new Thread(() -> {
           synchronized (obj) {
               System.out.println(Thread.currentThread() + "get lock");
               try {
                   obj.wait();
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
               System.out.println(Thread.currentThread() + "end wait");
           }
        });

        Thread threadB = new Thread(() -> {
            synchronized (obj) {
                System.out.println(Thread.currentThread() + "get lock");
                try {
                    obj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread() + "end wait");
            }
        });

        Thread threadC = new Thread(() -> {
            synchronized (obj) {
                System.out.println(Thread.currentThread() + "get lock");
//                obj.notify(); // 叫醒一个在obj监视中的wait状态的线程
                obj.notifyAll(); // 叫醒所有在obj监视中的wait状态的线程
            }
        });

        threadA.start();
        threadB.start();
        TimeUnit.SECONDS.sleep(1);
        threadC.start();
        threadA.join();
        threadB.join();
        threadC.join();
    }
}
