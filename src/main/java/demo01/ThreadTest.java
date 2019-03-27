package demo01;

/**
 * 创建线程的第一种方式(继承Thread类)
 */
public class ThreadTest {

    private static final class MyThread extends Thread {

        @Override
        public void run() {
            System.out.println("I am a child thread");
        }
    }

    public static void main(String[] args) {
        Thread thread = new MyThread();
        thread.start();
    }
}
