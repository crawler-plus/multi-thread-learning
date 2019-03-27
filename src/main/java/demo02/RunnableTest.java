package demo02;

/**
 * 创建线程的第二种方式(实现Runnable接口)
 */
public class RunnableTest {

    private static final class RunnableTask implements Runnable {

        @Override
        public void run() {
            System.out.println("I am a child thread");
        }
    }

    public static void main(String[] args) {
        Runnable runnableTask = new RunnableTask();
        new Thread(runnableTask).start();
        new Thread(runnableTask).start();
    }

}
