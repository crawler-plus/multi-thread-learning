package demo60;

import java.util.stream.IntStream;

/**
 * synchronized同步在Integer上失败，因为Integer会在++的时候创建一个新的Integer对象。
 */
public class SynchronizedIntegerBadTest implements Runnable {

    private static Integer i = 0;

    private static final SynchronizedIntegerBadTest instance = new SynchronizedIntegerBadTest();

    @Override
    public void run() {
        IntStream.range(0, 10000).forEach(ele -> {
            synchronized (i) { // 将i改为this就可以
                i ++;
            }
        });
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(i);
    }
}
