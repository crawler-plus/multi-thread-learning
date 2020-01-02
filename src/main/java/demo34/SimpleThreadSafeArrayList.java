package demo34;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 使用ReentrantReadWriteLock实现的线程安全的List,更为高效
 */
public class SimpleThreadSafeArrayList {

    private static final ArrayList<String> arrayList = new ArrayList<>();

    private static final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    private static final Lock readLock = lock.readLock();

    private static final Lock writeLock = lock.writeLock();

    public void add(String e) {
        writeLock.lock();
        try {
            arrayList.add(e);
        } finally {
            writeLock.unlock();
        }
    }

    public void remove(String e) {
        writeLock.lock();
        try {
            arrayList.remove(e);
        } finally {
            writeLock.unlock();
        }
    }

    public void get(int index) {
        readLock.lock();
        try {
            arrayList.get(index);
        } finally {
            readLock.unlock();
        }
    }
}
