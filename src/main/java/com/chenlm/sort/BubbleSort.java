package com.chenlm.sort;

import static com.chenlm.sort.SortTestHelper.swap;

public class BubbleSort {
    private BubbleSort() {
    }

    public static void sort(Comparable[] arr) {

//        int n = arr.length;
//        boolean swapped = false;
//
//        do {
//            swapped = false;
//            for (int i = 1; i < n; i++) {
//                if (arr[i - 1].compareTo(arr[i]) > 0) {
//                    swap(arr, i-1, i);
//                    swapped = true;
//                }
//            }
//        } while (swapped);

//        升级版

        int n = arr.length;
        int newn;

        do {
            newn = 0;
            for (int i = 1; i < n; i++) {
                if (arr[i - 1].compareTo(arr[i]) > 0) {
                    swap(arr, i - 1, i);
                    newn = i;
                }
            }
            n = newn;
        } while (newn > 0);
    }


}
