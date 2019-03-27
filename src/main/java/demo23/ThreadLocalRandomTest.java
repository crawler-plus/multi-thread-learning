package demo23;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 在多线程下，ThreadLocalRandom比Random效率更高
 */
public class ThreadLocalRandomTest {

    public static void main(String[] args) {

        ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();
        for(int i = 0; i < 5; i ++) {
            System.out.println(threadLocalRandom.nextInt());
            System.out.println(threadLocalRandom.nextInt(5));
            System.out.println(threadLocalRandom.nextLong());
            System.out.println(threadLocalRandom.nextDouble());
            System.out.println(threadLocalRandom.nextFloat());
            System.out.println(threadLocalRandom.nextBoolean());
            System.out.println("----------------");
        }
    }
}
