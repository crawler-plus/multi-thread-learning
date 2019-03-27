package demo22;

import java.util.Random;

/**
 * Random使用例子
 */
public class RandomTest {

    public static void main(String[] args) {

        Random random = new Random();
        for(int i = 0; i < 5; i ++) {
            System.out.println(random.nextInt());
            System.out.println(random.nextInt(5));
            System.out.println(random.nextLong());
            System.out.println(random.nextDouble());
            System.out.println(random.nextFloat());
            System.out.println(random.nextBoolean());
            System.out.println("----------------");
        }
    }
}
