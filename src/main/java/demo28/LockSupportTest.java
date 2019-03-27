package demo28;

import java.util.concurrent.locks.LockSupport;

/**
 * LockSupport基本使用
 */
public class LockSupportTest {

    public static void main(String[] args) {
        System.out.println("begin park");
        // 使用当前线程获取到许可证
        LockSupport.unpark(Thread.currentThread());
        // 等待当前线程的一个许可证
        LockSupport.park();
        System.out.println("after park");
    }
}
