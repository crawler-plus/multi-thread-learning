package demo03;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * 创建线程的第三种方式(实现Callable接口,有返回值)
 */
public class FutureTaskTest {

    private static final class CallerTask implements Callable<String> {

        @Override
        public String call() {
            return "hello, world";
        }
    }

    public static void main(String[] args) throws Exception {
        FutureTask<String> futureTask = new FutureTask<>(new CallerTask());
        new Thread(futureTask).start();
        // 等待任务执行完毕，返回结果
        String s = futureTask.get();
        System.out.println(s);
    }
}
