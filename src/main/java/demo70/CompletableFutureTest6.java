package demo70;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * CompletableFuture使用例子
 */
public class CompletableFutureTest6 {

    private static Integer calc(Integer param) {
        return param / 2;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> intFuture1 = CompletableFuture.supplyAsync(() -> calc(50));
        CompletableFuture<Integer> intFuture2 = CompletableFuture.supplyAsync(() -> calc(30));
        CompletableFuture future = intFuture1.thenCombine(intFuture2, (i, j) -> i + j)
                .thenApply(str -> str + " hello")
                .thenAccept(System.out::println);
        future.get();
    }
}
