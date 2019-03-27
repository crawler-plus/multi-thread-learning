package demo16;

/**
 * ThreadLocal例子
 */
public class ThreadLocalTest {

    private static final ThreadLocal<String> localVariable = new ThreadLocal<>();

    private static void print(String str) {
        System.out.println(str + ":" + localVariable.get());
        localVariable.remove(); // 清除threadLocal中的值
    }

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            localVariable.set("thread1 local variable");
            print("thread1");
            System.out.println(localVariable.get());
        });

        Thread thread2 = new Thread(() -> {
            localVariable.set("thread2 local variable");
            print("thread2");
            System.out.println(localVariable.get());
        });

        thread1.start();
        thread2.start();
    }
}
