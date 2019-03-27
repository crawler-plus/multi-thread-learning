package demo32;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 生产者消费者模型
 */
public class ProducerConsumerTest {

    private static final Lock lock = new ReentrantLock();

    private static final Condition notFull = lock.newCondition();

    private static final Condition notEmpty = lock.newCondition();

    private static final Queue<String> queue = new LinkedBlockingDeque<>();

    private static int queueSize = 10;

    private static class Producer extends Thread {

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                lock.lock();
                try {
                    while (queue.size() == queueSize) {
                        notEmpty.await();
                    }
                    queueSize++;
                    queue.add("element");
                    System.out.println("element added");
                    notFull.signalAll();
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    private static class Consumer extends Thread {

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                lock.lock();
                try {
                    while (queue.size() == 0) {
                        notFull.await();
                    }
                    queueSize--;
                    String poll = queue.poll();
                    System.out.println("get element:" + poll);
                    notEmpty.signalAll();
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    public static void main(String[] args) {
        new Producer().start();
        new Producer().start();
        new Consumer().start();
        new Consumer().start();
        new Consumer().start();
    }
}
