package com.chenlm.sort;

public class InsertionSort {
    private InsertionSort() {

    }
    // 对arr[l...r]的区间使用InsertionSort排序
    public static void sort(Comparable[] arr, int l, int r){

        for( int i = l + 1 ; i <= r ; i ++ ){
            Comparable e = arr[i];
            int j = i;
            for( ; j > l && arr[j-1].compareTo(e) > 0 ; j--)
                arr[j] = arr[j-1];
            arr[j] = e;
        }
    }

    public static void sort(Comparable[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
//            第一种
//            for (int j = i; j > 0; j --) {
//                if (arr[j].compareTo(arr[j - 1]) < 0) {
//                    swap(arr, j, j - 1);
//                } else {
//                    break;
//                }
//            }

//            第二种
//            for (int j = i; j > 0 && arr[j].compareTo(arr[j - 1]) < 0; j--) {
//                swap(arr, j, j - 1);
//            }

//            第三种
            Comparable e = arr[i];
            int j = i;
            for (; j > 0 && arr[j - 1].compareTo(e) > 0; j--) {
                arr[j] = arr[j - 1];
            }
            arr[j] = e;

        }
    }

    // 测试InsertionSort
    public static void main(String[] args) {

//        int N = 20000;
//        Integer[] arr = SortTestHelper.generateRandomArray(N, 0, 100000);
//        SortTestHelper.testSort("com.chenlm.sort.InsertionSort", arr);

        return;
    }
}
