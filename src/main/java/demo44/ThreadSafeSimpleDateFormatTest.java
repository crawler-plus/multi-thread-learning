package demo44;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * 效率较高的SimpleDateFormat实现，使用ThreadLocal
 */
public class ThreadSafeSimpleDateFormatTest {

    private static final ThreadLocal<DateFormat> simpleDateFormat = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

    public static void main(String[] args) {
        for(int i = 0; i < 10; i ++) {
            Thread thread = new Thread(() -> {
                try {
                    System.out.println(simpleDateFormat.get().parse("2019-03-09 14:10:10"));
                }catch (Exception ex) {
                    ex.printStackTrace();
                }finally {
                    simpleDateFormat.remove(); // 避免内存泄漏
                }
            });
            thread.start();
        }
    }
}
