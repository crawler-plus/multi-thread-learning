package demo48;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 创建线程池的时候，注意指定线程池的名称，容器排查问题
 */
public class ThreadPoolNameTest {

    private static final ThreadPoolExecutor threadPoolExecutor1 =
            new ThreadPoolExecutor(5, 5, 1, TimeUnit.MINUTES, new LinkedBlockingQueue<>(),
                    r -> {
                        Thread thread = new Thread(r);
                        thread.setName("save order");
                        return thread;
                    });

    private static final ThreadPoolExecutor threadPoolExecutor2 =
            new ThreadPoolExecutor(5, 5, 1, TimeUnit.MINUTES, new LinkedBlockingQueue<>(),
                    r -> {
                        Thread thread = new Thread(r);
                        thread.setName("save addr");
                        return thread;
                    });

    public static void main(String[] args) {

        threadPoolExecutor1.execute(() -> {
            System.out.println("保存订单线程");
            throw new NullPointerException();
        });

        threadPoolExecutor2.execute(() -> {
            System.out.println("保存收货地址线程");
        });

        threadPoolExecutor1.shutdown();
        threadPoolExecutor2.shutdown();
    }

}
