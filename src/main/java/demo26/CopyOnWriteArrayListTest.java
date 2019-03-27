package demo26;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 演示了CopyOnWriteArrayList的基本使用和迭代器的弱一致性
 */
public class CopyOnWriteArrayListTest {

    private static final CopyOnWriteArrayList<String> copyOnWriteArrayList = new CopyOnWriteArrayList<>();

    public static void main(String[] args) throws Exception {
        copyOnWriteArrayList.add("hello");
        copyOnWriteArrayList.add("world");
        copyOnWriteArrayList.add("welcome");

        Thread thread1 = new Thread(() -> {
            copyOnWriteArrayList.set(1, "haha");
            copyOnWriteArrayList.remove(2);
        });

        // 获取迭代器（在写线程修改之前获取），获取迭代器的瞬间，其他线程对这个list的修改都是无效的，此时copyOnWriteArrayList不可变。
        Iterator<String> iterator = copyOnWriteArrayList.iterator();

        thread1.start();
        thread1.join();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
