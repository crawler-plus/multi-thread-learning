package demo36;

import java.util.Random;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 延迟队列例子
 */
public class DelayQueueTest {

    private static class DelayedTask implements Delayed {

        private long delayTime; // 延迟时间

        private long expire;

        private String taskName;

        public DelayedTask(long delay, String taskName) {
            this.delayTime = delay;
            this.taskName = taskName;
            expire = System.currentTimeMillis() + delay;
        }

        /**
         * 剩余时间＝到期时间 - 当前时间
         */
        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(this.expire - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        /**
         * 优先队列里的优先级规则
         *
         * @param o
         * @return
         */
        @Override
        public int compareTo(Delayed o) {
            return (int) (this.getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS));
        }

        @Override
        public String toString() {
            return "delay=" + delayTime + ", " + "expire=" + expire + ", " + "taskName=" + taskName;
        }
    }

    public static void main(String[] args) {
        DelayQueue<DelayedTask> delayQueue = new DelayQueue<>();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            DelayedTask delayedTask = new DelayedTask(random.nextInt(500), "task:" + i);
            delayQueue.offer(delayedTask);
        }
        DelayedTask ele;
        try {
            for (; ; ) {
                while ((ele = delayQueue.take()) != null) {
                    System.out.println(ele);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
