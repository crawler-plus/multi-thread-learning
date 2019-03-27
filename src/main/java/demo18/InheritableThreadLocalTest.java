package demo18;

/**
 * InheritableThreadLocal实现ThreadLocal的可继承性
 */
public class InheritableThreadLocalTest {

    private static final ThreadLocal<String> threadLocal = new InheritableThreadLocal<>();

    public static void main(String[] args) {

        threadLocal.set("hello, world");

        new Thread(() -> {
            System.out.println(Thread.currentThread() + "-" + threadLocal.get()); // 子线程可以获取到父线程中设置到threadLocal中的值
        }).start();

        System.out.println(Thread.currentThread() + "-" + threadLocal.get()); // 父线程可以获取到threadLocal中的值
    }
}
