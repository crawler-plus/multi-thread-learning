package demo70;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * CompletableFuture使用例子
 */
public class CompletableFutureTest5 {

    private static Integer calc(Integer param) {
        return param / 2;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> calc(50))
                .thenCompose(i -> CompletableFuture.supplyAsync(() -> calc(i)))
                        .thenApply(str -> str + " hello")
                        .thenAccept(System.out::println);
        future.get();
    }
}
