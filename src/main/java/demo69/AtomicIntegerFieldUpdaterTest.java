package demo69;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * AtomicIntegerFieldUpdater应用案例
 */
public class AtomicIntegerFieldUpdaterTest {

    private static class Candidate {

        /**
         * 1：score变量不能声明为private的，因为使用反射
         * 2：必须为volatile类型的
         * 3：不能为static类型的
         */
        volatile int score;
    }

    private static final AtomicIntegerFieldUpdater<Candidate> scoreUpdater = AtomicIntegerFieldUpdater.newUpdater(Candidate.class, "score");

    private static final AtomicInteger allScore = new AtomicInteger(0);

    public static void main(String[] args) throws Exception {
        final Candidate stu = new Candidate();
        Thread[] t = new Thread[10000];
        for (int i = 0; i < t.length; i++) {
            t[i] = new Thread(() -> {
                if (Math.random() > 0.4) {
                    scoreUpdater.incrementAndGet(stu);
                    allScore.incrementAndGet();
                }
            });
            t[i].start();
        }
        for (int i = 0; i < t.length; i++) {
            t[i].join();
        }
        System.out.println("score=" + stu.score);
        System.out.println("allScore=" + allScore);
    }
}
