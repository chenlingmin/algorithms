package com.chenlm.graph;

public class MinHeap<Item extends Comparable> {

    private Item[] data;
    private int count;
    private int capacity;

    public MinHeap(int capacity) {
        assert capacity >= 0;
        data = (Item[]) new Comparable[capacity + 1];
        count = 0;
        this.capacity = capacity;
    }

    public MinHeap(Item arr[]) {
        this(arr.length);
        for (int i = 0; i < capacity; i++) {
            data[i + 1] = arr[i];
        }
        count = capacity;
        for (int i = count / 2; i >= 1; i--) {
            shiftDown(i);
        }
    }

    // 返回堆中的元素个数
    public int size() {
        return count;
    }

    // 返回一个布尔值, 表示堆中是否为空
    public boolean isEmpty() {
        return count == 0;
    }

    public void insert(Item item) {
        assert count + 1 <= capacity;
        data[count + 1] = item;
        count++;
        shiftUp(count);
    }

    public Item extractMin() {
        assert count > 0;
        Item ret = data[1];
        swap(1, count);
        count--;
        shiftDown(1);

        return ret;
    }

    // 获取最小堆中的堆顶元素
    public Item getMin() {
        assert (count > 0);
        return data[1];
    }

    private void shiftUp(int k) {
        while (k > 1 && data[k / 2].compareTo(data[k]) > 0) {
            swap(k, k / 2);
            k /= 2;
        }
    }

    private void shiftDown(int k) {
        while (2 * k <= count) {
            int j = 2 * k;
            if (j + 1 <= count && data[j + 1].compareTo(data[j]) < 0) {
                j++;
            }
            if (data[k].compareTo(data[j]) <= 0) {
                break;
            }
            swap(k, j);
            k = j;
        }
    }

    private void swap(int i, int j) {
        Item t = data[i];
        data[i] = data[j];
        data[j] = t;
    }

}
