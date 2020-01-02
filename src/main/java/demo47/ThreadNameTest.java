package demo47;

/**
 * 创建线程的时候，注意指定线程的名称，容易排查问题
 */
public class ThreadNameTest {

    private static final String THREAD_SAVE_ORDER = "THREAD_SAVE_ORDER";

    private static final String THREAD_SAVE_ADDR = "THREAD_SAVE_ADDR";

    public static void main(String[] args) {

        Thread thread1 = new Thread(() -> {
            System.out.println("保存订单线程");
            throw new NullPointerException();
        }, THREAD_SAVE_ORDER);

        Thread thread2 = new Thread(() -> {
            System.out.println("保存收货地址线程");
            throw new NullPointerException();
        }, THREAD_SAVE_ADDR);

        thread1.start();
        thread2.start();
    }
}
