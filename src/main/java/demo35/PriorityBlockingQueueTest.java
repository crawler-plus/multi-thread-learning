package demo35;

import java.util.Random;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * 优先级队列示例
 */
public class PriorityBlockingQueueTest {

    private static class Task implements Comparable<Task> {

        private int priority; // 优先级

        private String taskName;

        public int getPriority() {
            return priority;
        }

        public void setPriority(int priority) {
            this.priority = priority;
        }

        public String getTaskName() {
            return taskName;
        }

        public void setTaskName(String taskName) {
            this.taskName = taskName;
        }

        @Override
        public int compareTo(Task o) {
            if(this.priority > o.getPriority()) {
                return 1;
            }
            return -1;
        }

        public void doSomeThing() {
            System.out.println(taskName + ":" + priority);
        }
    }

    public static void main(String[] args) {
        PriorityBlockingQueue<Task> priorityBlockingQueue = new PriorityBlockingQueue<>();
        Random random = new Random();
        for(int i = 0; i < 10; i ++) {
            Task task = new Task();
            task.setPriority(random.nextInt(100));
            task.setTaskName("taskName" + i);
            priorityBlockingQueue.offer(task);
        }
        while (!priorityBlockingQueue.isEmpty()) {
            Task task = priorityBlockingQueue.poll();
            if(null != task) {
                task.doSomeThing();
            }
        }
    }
}
