package demo21;

/**
 * synchronized是可以重入的
 */
public class SynchronizedReentrantTest {

    private static synchronized void helloA() {
        System.out.println("hello A");
    }

    private static synchronized void helloB() {
        System.out.println("hello B");
        helloA();
    }

    public static void main(String[] args) {
        helloB();
    }
}
