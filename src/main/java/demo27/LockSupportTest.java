package demo27;

import java.util.concurrent.locks.LockSupport;

/**
 * LockSupport基本使用
 */
public class LockSupportTest {

    public static void main(String[] args) {
        System.out.println("begin park");
        // 当调用park方法时，当前线程被挂起
        LockSupport.park();
        System.out.println("after park");
    }
}
