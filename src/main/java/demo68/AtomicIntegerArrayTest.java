package demo68;

import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.stream.IntStream;

/**
 * AtomicIntegerArray使用例子
 */
public class AtomicIntegerArrayTest {

    private static AtomicIntegerArray arr = new AtomicIntegerArray(2);

    private static class AddThread implements Runnable {
        // (0 + 1) * 10000 * 5 / 2
        @Override
        public void run() {
            IntStream.range(0, 10000).forEach(item -> arr.getAndIncrement(item % arr.length()));
        }
    }

    public static void main(String[] args) throws Exception {
        Thread[] ts = new Thread[5];
        for (int i = 0; i < ts.length; i++) {
            ts[i] = new Thread(new AddThread());
        }
        for (int i = 0; i < ts.length; i++) {
            ts[i].start();
        }
        for (int i = 0; i < ts.length; i++) {
            ts[i].join();
        }
        System.out.println(arr);
    }
}
