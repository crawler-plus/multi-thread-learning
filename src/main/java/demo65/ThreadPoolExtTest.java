package demo65;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * 扩展线程池
 */
public class ThreadPoolExtTest {

    private static class MyTask implements Runnable {

        private String name;

        public MyTask(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        public void run() {
            System.out.println("正在执行:Thread ID:" + Thread.currentThread().getId() + ", task name=" + this.name);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = new ThreadPoolExecutor(5, 5, 0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>()){

            @Override
            protected void beforeExecute(Thread t, Runnable r) {
                System.out.println("准备执行:" + ((MyTask) r).getName());
            }

            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                System.out.println("执行完成:" + ((MyTask) r).getName());
            }

            @Override
            protected void terminated() {
                System.out.println("线程池退出");
            }
        };
        IntStream.range(0, 5).forEach(item -> {
            MyTask myTask = new MyTask("task-" + item);
            executorService.execute(myTask);
        });
        executorService.shutdown();
    }
}
