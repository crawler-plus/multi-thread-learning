package demo43;

import java.text.SimpleDateFormat;

/**
 * 效率较低的SimpleDateFormat实现，使用同步锁方式
 */
public class ThreadSafeSimpleDateFormatTest {

    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {
        for(int i = 0; i < 10; i ++) {
            Thread thread = new Thread(() -> {
                try {
                    synchronized (simpleDateFormat) {
                        System.out.println(simpleDateFormat.parse("2019-03-09 14:10:10"));
                    }
                }catch (Exception ex) {
                    ex.printStackTrace();
                }
            });
            thread.start();
        }
    }
}
