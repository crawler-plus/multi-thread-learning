package demo64;

import java.util.concurrent.*;
import java.util.stream.IntStream;

/**
 * 线程池拒绝策略
 */
public class ThreadPoolRejectedExecutionTest {

    private static class MyTask implements Runnable {

        @Override
        public void run() {
            System.out.println(System.currentTimeMillis() + ":Thread ID:" + Thread.currentThread().getId());
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        MyTask myTask = new MyTask();
        ExecutorService executorService = new ThreadPoolExecutor(5, 5, 0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(10), Executors.defaultThreadFactory(),
                (r, exe) -> System.out.println(r.toString() + "is discard"));
        IntStream.range(0, 100).forEach(each -> executorService.submit(myTask));
        executorService.shutdown();
    }
}
