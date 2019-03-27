package demo10;

/**
 *  一个线程中调用另一个正在睡眠线程中的interrupt方法，会让那个睡眠的线程抛出异常！
 */
public class SleepInterruptTest {

    public static void main(String[] args) throws Exception {
        Thread threadA = new Thread(() -> {
            try {
                System.out.println(Thread.currentThread() + "is in sleep");
                Thread.sleep(5000);
                System.out.println(Thread.currentThread() + "is in awaked");
            } catch (InterruptedException e) {
                System.out.println("sleep interrupted!");
            }
        });

        threadA.start();
        Thread.sleep(1000);
        // 在主线程中，线程A在睡眠过程中将线程A打断，则会在线程A的sleep方法出抛出异常！
        threadA.interrupt();
    }
}
