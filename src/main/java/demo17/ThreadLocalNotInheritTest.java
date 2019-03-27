package demo17;

/**
 * ThreadLocal不可继承性
 */
public class ThreadLocalNotInheritTest {

    private static final ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {

        threadLocal.set("hello, world");

        new Thread(() -> {
            System.out.println(Thread.currentThread() + "-" + threadLocal.get()); // 子线程获取不到父线程中设置到threadLocal中的值，返回null
        }).start();

        System.out.println(Thread.currentThread() + "-" + threadLocal.get()); // 父线程可以获取到threadLocal中的值
    }
}
