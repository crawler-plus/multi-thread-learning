package demo71;

import com.google.common.util.concurrent.RateLimiter;

import java.util.stream.IntStream;

/**
 * guava限流，每秒只能通过2个请求
 */
public class RateLimiterTest1 {

    private static final RateLimiter rateLimiter = RateLimiter.create(2);

    private static class Task implements Runnable {

        public void run() {
            System.out.println(System.currentTimeMillis());
        }
    }

    public static void main(String[] args) {
        IntStream.range(0, 50).forEach(t -> {
            rateLimiter.acquire();
            new Thread(new Task()).start();
        });
    }
}
