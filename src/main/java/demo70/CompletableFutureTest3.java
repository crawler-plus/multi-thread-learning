package demo70;

import java.util.concurrent.CompletableFuture;

/**
 * CompletableFuture使用例子
 */
public class CompletableFutureTest3 {

    private static Integer calc(Integer param) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return param * param;
    }

    public static void main(String[] args) throws Exception {
        CompletableFuture<Void> completableFuture = CompletableFuture.supplyAsync(() -> calc(50))
                .thenApply(i -> Integer.toString(i))
                .thenApply(str -> str + " hello")
                .thenAccept(System.out::println);
        completableFuture.get();
    }
}
