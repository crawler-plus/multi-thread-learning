package demo71;

import com.google.common.util.concurrent.RateLimiter;

/**
 * guava限流，获取不到的请求会被抛弃
 */
public class RateLimiterTest2 {

    private static final RateLimiter rateLimiter = RateLimiter.create(2);

    private static class Task implements Runnable {

        public void run() {
            System.out.println(System.currentTimeMillis());
        }
    }

    public static void main(String[] args) {
        for(int i = 0; i < 50; i ++) {
            if(!rateLimiter.tryAcquire()) {
                continue;
            }
            new Thread(new Task()).start();
        }
    }
}
