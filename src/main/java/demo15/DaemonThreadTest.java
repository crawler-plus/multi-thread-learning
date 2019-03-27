package demo15;

/**
 * 守护线程例子
 */
public class DaemonThreadTest {

    public static void main(String[] args) {
        Thread t = new Thread(() ->{
            for(;;) {}
        });
        // 设置为true表示t是一个守护线程，此时主线程执行完成之后子线程也就结束了。默认子线程是用户线程
        t.setDaemon(true);
        t.start();
    }
}
