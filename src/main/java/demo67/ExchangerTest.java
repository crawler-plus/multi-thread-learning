package demo67;

import java.util.concurrent.Exchanger;

/**
 * exchanger使用案例
 */
public class ExchangerTest {

    private static class MyExchanger extends Thread {

        private Exchanger<String> exchanger;

        private String string;

        private String threadName;

        public MyExchanger(Exchanger<String> exchanger, String string, String threadName) {
            this.exchanger = exchanger;
            this.string = string;
            this.threadName = threadName;
        }

        @Override
        public void run() {
            try {
                System.out.println(threadName + ": " + exchanger.exchange(string));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<>();
        Thread t1 = new MyExchanger(exchanger, "string1", "thread1");
        Thread t2 = new MyExchanger(exchanger, "string2", "thread2");
        t1.start();
        t2.start();
    }
}
