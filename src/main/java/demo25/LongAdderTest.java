package demo25;

import java.util.concurrent.atomic.LongAdder;

/**
 * 高并发环境下，LongAdder比AtomicLong性能更好
 */
public class LongAdderTest {

    private static final LongAdder longAdder = new LongAdder();

    private static final Integer[] array1 = new Integer[]{0, 1, 2, 3, 4, 5, 0, 8, 0};

    private static final Integer[] array2 = new Integer[]{11, 12, 23, 0, 99, 0, 0, 23, 0, 70};

    public static void main(String[] args) throws Exception {

        Thread t1 = new Thread(() -> {
            int size = array1.length;
            for(int i = 0; i < size; i ++) {
                if(array1[i].intValue() == 0) {
                    longAdder.increment();
                }
            }
        });

        Thread t2 = new Thread(() -> {
            int size = array2.length;
            for(int i = 0; i < size; i ++) {
                if(array2[i].intValue() == 0) {
                    longAdder.increment();
                }
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(longAdder.sum());
    }
}
