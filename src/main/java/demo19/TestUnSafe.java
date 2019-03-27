package demo19;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * 通过反射的方式使用UnSafe
 */
public class TestUnSafe {

    private static Unsafe unsafe;

    private static long stateOffset;

    private long state;

    static {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe = (Unsafe)field.get(null);
            // 获取state在TestUnSafe中的偏移量
            stateOffset = unsafe.objectFieldOffset(TestUnSafe.class.getDeclaredField("state"));
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        TestUnSafe testUnSafe = new TestUnSafe();
        boolean b = unsafe.compareAndSwapInt(testUnSafe, stateOffset, 0, 1);
        System.out.println(b);
    }
}
