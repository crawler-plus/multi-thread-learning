package demo70;

import java.util.concurrent.CompletableFuture;

/**
 * CompletableFuture使用例子
 */
public class CompletableFutureTest1 {

    private static class AskThread implements Runnable {

        private CompletableFuture<Integer> re;

        public AskThread(CompletableFuture<Integer> re) {
            this.re = re;
        }

        @Override
        public void run() {
            int myRe = 0;
            try {
                myRe = re.get() * re.get();
            } catch (Exception ex) {
            }
            System.out.println(myRe);
        }
    }

    public static void main(String[] args) throws Exception {
        final CompletableFuture<Integer> future = new CompletableFuture<>();
        new Thread(new AskThread(future)).start();
        Thread.sleep(1000);
        future.complete(60);
    }
}
