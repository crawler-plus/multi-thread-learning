package demo45;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 当一个Timer运行多个TimerTask时，只要其中的一个TimerTask在执行中向run方法外抛出异常，则其他任务也会终止！
 */
public class TimerErrorTest {

    private static final Timer timer = new Timer();

    public static void main(String[] args) {
        // 添加任务1，延迟500ms执行
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("one task");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                throw new RuntimeException("error!");
            }
        }, 500);

        // 添加任务2，延迟1000ms执行
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                for(;;) {
                    System.out.println("two task");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, 1000);
    }
}
