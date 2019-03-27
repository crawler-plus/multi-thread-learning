package demo29;

import java.util.concurrent.locks.LockSupport;

/**
 * LockSupport基本使用
 */
public class LockSupportTest {

    public static void main(String[] args) throws Exception {

        Thread thread = new Thread(() -> {
            System.out.println("child thread begin park!");
            // 挂起自己
            LockSupport.park();
            System.out.println("child thread unpark!");
        });

        thread.start();
        Thread.sleep(1000);
        System.out.println("main thread begin unpark");
        // 调用unpark方法让thread线程持有许可证，然后park方法返回
        LockSupport.unpark(thread);
    }
}
