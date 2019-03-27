package demo31;

import java.util.concurrent.locks.LockSupport;

/**
 * LockSupport基本使用
 */
public class LockSupportTest {

    public static void main(String[] args) {
        System.out.println("begin park");
        // 如果没有拿到许可证，则调用线程会被挂起nanos时间后修改为自动返回。(nanos单位为纳秒)
        LockSupport.parkNanos(1000 * 1000 * 1000);
        System.out.println("after park");
    }
}
