package demo70;

import java.util.concurrent.CompletableFuture;

/**
 * CompletableFuture使用例子
 */
public class CompletableFutureTest2 {

    private static Integer calc(Integer param) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return param * param;
    }

    public static void main(String[] args) throws Exception {
        final CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> calc(50));
        System.out.println(future.get());
    }
}
