package demo11;

import java.util.concurrent.TimeUnit;

/**
 * Thread.yield方法案例
 */
public class YieldTest implements Runnable {

    public YieldTest() {
        new Thread(this).start();
    }

    @Override
    public void run() {
        for(int i = 0; i < 5; i ++) {
            if((i % 5) == 0) {
                System.out.println(Thread.currentThread() + "yield cpu");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Thread.yield(); // 执行到这里的时候，当前线程尝试让出cpu给下一个线程使用
            }
        }
        System.out.println(Thread.currentThread() + "is over");
    }

    public static void main(String[] args) {
        new YieldTest();
        new YieldTest();
        new YieldTest();
    }
}
