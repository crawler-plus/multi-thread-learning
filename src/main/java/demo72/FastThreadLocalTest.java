package demo72;

import io.netty.util.concurrent.FastThreadLocal;
import io.netty.util.concurrent.FastThreadLocalThread;

/**
 * 演示FastThreadLocal和FastThreadLocalThread
 */
public class FastThreadLocalTest {

    private static final FastThreadLocal<String> fastThreadLocal = new FastThreadLocal<>();

    public static void main(String[] args) {
        new FastThreadLocalThread(() -> {
            fastThreadLocal.set("variable from t1");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(fastThreadLocal.get());
        }, "t1").start();
        new FastThreadLocalThread(() -> {
            fastThreadLocal.set("variable from t2");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(fastThreadLocal.get());
        }, "t1").start();
    }
}
