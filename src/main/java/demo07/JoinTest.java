package demo07;

import java.util.concurrent.TimeUnit;

/**
 * 演示join方法，join方法调用的时候，不一定是按照调用的顺序等待的，如下面的程序
 */
public class JoinTest {

    public static void main(String[] args) throws Exception {

        Thread threadOne = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread() + "over!");
        });

        Thread threadTwo = new Thread(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread() + "over!");
        });

        threadOne.start();
        threadTwo.start();
        threadOne.join(); // 等待该线程结束返回
        threadTwo.join(); // 等待该线程结束返回
        System.out.println("main thread over!");
    }
}
