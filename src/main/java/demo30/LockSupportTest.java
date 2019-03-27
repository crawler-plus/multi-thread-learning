package demo30;

import java.util.concurrent.locks.LockSupport;

/**
 * LockSupport基本使用
 */
public class LockSupportTest {

    public static void main(String[] args) throws Exception {

        Thread thread = new Thread(() -> {
            System.out.println("child thread begin park!");
            while (!Thread.currentThread().isInterrupted()) {
                // 挂起自己
                LockSupport.park();
            }
            System.out.println("child thread unpark!");
        });

        thread.start();
        Thread.sleep(1000);
        System.out.println("main thread begin unpark");
        // 只有中断子线程，子线程才会运行结束，如果子线程不被中断 即使你调用 unpark(thread) 方法子线程也不会结束。
        LockSupport.unpark(thread);
//        thread.interrupt();
    }
}
