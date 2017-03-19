package com.smj.upload;

import java.util.LinkedList;

/**
 * Created by chenminjie on 17/3/19.
 */

public class UploadQueue<E> {
    private LinkedList<E> list = new LinkedList<E>();
    private int size = 0;

    public synchronized void put(E e) {
        size++;
        list.addLast(e);
    }

    // 使用removeFirst()方法，返回队列中第一个数据，然后将它从队列中删除
    public synchronized E get() {
        size--;
        return list.removeFirst();
    }

    public synchronized boolean empty() {
        boolean flag = false;
        if (size == 0) {
            flag = true;
        }
        return flag;
    }

    public synchronized int size() {
        return size;
    }

    public synchronized void clear() {
        list.clear();
        size = 0;
    }

    public synchronized E get(int i) {
        return list.get(i);
    }
}
