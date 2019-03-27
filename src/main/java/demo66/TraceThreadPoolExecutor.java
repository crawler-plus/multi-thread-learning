package demo66;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 扩展ThreadPoolExecutor线程池，可以在出现异常的时候定位这个任务在哪里提交
 */
public class TraceThreadPoolExecutor extends ThreadPoolExecutor {

    public TraceThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit timeUnit,
                                   BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, timeUnit, workQueue);
    }

    @Override
    public void execute(Runnable command) {
        super.execute(wrap(command, clientTrace()));
    }

    @Override
    public Future<?> submit(Runnable task) {
        return super.submit(wrap(task, clientTrace()));
    }

    private Exception clientTrace() {
        return new Exception("Client stack trace");
    }

    private Runnable wrap(final Runnable runnable, final Exception clientStack) {
        return () -> {
            try {
                runnable.run();
            }catch (Exception ex) {
                clientStack.printStackTrace();
                throw ex;
            }
        };
    }
}
