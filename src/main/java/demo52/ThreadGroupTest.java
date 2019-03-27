package demo52;

/**
 * 线程组例子
 */
public class ThreadGroupTest implements Runnable {

    @Override
    public void run() {
        String groupAndName = Thread.currentThread().getThreadGroup().getName()
                + "-" + Thread.currentThread().getName();
        while (true) {
            System.out.println("I am" + groupAndName);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ThreadGroup tg = new ThreadGroup("PrintGroup");
        Thread t1 = new Thread(tg, new ThreadGroupTest(), "T1");
        Thread t2 = new Thread(tg, new ThreadGroupTest(), "T2");
        t1.start();
        t2.start();
        System.out.println("activeCount:" + tg.activeCount());
        tg.list();
    }
}
