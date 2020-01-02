package demo33;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用ReentrantLock实现的线程安全的List
 */
public class SimpleThreadSafeArrayList {

    private static final ArrayList<String> arrayList = new ArrayList<>();

    private static final Lock lock = new ReentrantLock();

    public void add(String e) {
        lock.lock();
        try {
            arrayList.add(e);
        } finally {
            lock.unlock();
        }
    }

    public void remove(String e) {
        lock.lock();
        try {
            arrayList.remove(e);
        } finally {
            lock.unlock();
        }
    }

    public void get(int index) {
        lock.lock();
        try {
            arrayList.get(index);
        } finally {
            lock.unlock();
        }
    }
}
