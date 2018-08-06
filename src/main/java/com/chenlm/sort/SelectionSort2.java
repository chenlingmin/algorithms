package com.chenlm.sort;

public class SelectionSort2 {

    private SelectionSort2() {
    }

    public static void sort(Comparable[] arr) {
        int left = 0, right = arr.length - 1;
        while (left < right) {

            int minIndex = left;
            int maxIndex = right;

            if (arr[minIndex].compareTo(arr[maxIndex]) > 0) {
                swap(arr, minIndex, maxIndex);
            }

            for (int i = left + 1; i < right; i++) {
                if (arr[i].compareTo(arr[minIndex]) < 0) {
                    minIndex = i;
                } else if (arr[i].compareTo(arr[maxIndex]) > 0) {
                    maxIndex = i;
                }
            }

            swap(arr, left, minIndex);
            swap(arr, right, maxIndex);

            left++;
            right--;
        }
    }

    private static void swap(Object[] arr, int i, int j) {
        Object t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static void main(String[] args) {
// 测试排序算法辅助函数
        int N = 20000;
        Integer[] arr = SortTestHelper.generateRandomArray(N, 0, 100000);
        SortTestHelper.testSort("com.chenlm.sort.SelectionSort2", arr);

        return;
    }
}
