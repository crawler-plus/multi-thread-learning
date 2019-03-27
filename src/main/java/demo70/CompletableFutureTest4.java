package demo70;

import java.util.concurrent.CompletableFuture;

/**
 * CompletableFuture使用例子
 */
public class CompletableFutureTest4 {

    private static Integer calc(Integer param) {
        return param / 0;
    }

    public static void main(String[] args) throws Exception {
        CompletableFuture<Void> completableFuture = CompletableFuture.supplyAsync(() -> calc(50))
                .exceptionally(ex -> {
                    System.out.println(ex.toString());
                    return 0;
                })
                .thenApply(i -> Integer.toString(i))
                .thenApply(str -> str + " hello")
                .thenAccept(System.out::println);
        completableFuture.get();
    }
}
