package demo24;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 多线程并发执行计数操作，保证线程安全的例子，AtomicXXX基于CAS实现
 */
public class AtomicTest {

    private static final AtomicLong atomicLong = new AtomicLong();

    private static final Integer[] array1 = new Integer[]{0, 1, 2, 3, 4, 5, 0, 8, 0};

    private static final Integer[] array2 = new Integer[]{11, 12, 23, 0, 99, 0, 0, 23, 0, 70};

    public static void main(String[] args) throws Exception {

        Thread t1 = new Thread(() -> {
            int size = array1.length;
            for (int i = 0; i < size; i++) {
                if (array1[i].intValue() == 0) {
                    atomicLong.incrementAndGet();
                }
            }
        });

        Thread t2 = new Thread(() -> {
            int size = array2.length;
            for (int i = 0; i < size; i++) {
                if (array2[i].intValue() == 0) {
                    atomicLong.incrementAndGet();
                }
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(atomicLong.get());
    }
}
