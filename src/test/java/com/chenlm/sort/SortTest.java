package com.chenlm.sort;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class SortTest {

    @Test
    public void BubbleSort() {
        Integer[] integers = SortTestHelper.generateRandomArray(200, 0, 100);
        BubbleSort.sort(integers);
        assertTrue(SortTestHelper.isSorted(integers));
    }

    @Test
    public void InsertionSort() {
        Integer[] integers = SortTestHelper.generateRandomArray(200, 0, 100);
        InsertionSort.sort(integers);
        assertTrue(SortTestHelper.isSorted(integers));
    }

    @Test
    public void SelectionSort() {
        Integer[] integers = SortTestHelper.generateRandomArray(200, 0, 100);
        SelectionSort.sort(integers);
        assertTrue(SortTestHelper.isSorted(integers));
    }

    @Test
    public void SelectionSort2() {
        Integer[] integers = SortTestHelper.generateRandomArray(200, 0, 100);
        SelectionSort2.sort(integers);
        assertTrue(SortTestHelper.isSorted(integers));
    }

    @Test
    public void ShellSort() {
        Integer[] integers = SortTestHelper.generateRandomArray(200, 0, 100);
        ShellSort.sort(integers);
        assertTrue(SortTestHelper.isSorted(integers));
    }

    @Test
    public void MergeSort() {
        Integer[] integers = SortTestHelper.generateRandomArray(200, 0, 100);
        MergeSort.sort(integers);
        assertTrue(SortTestHelper.isSorted(integers));
    }

    @Test
    public void MergeSortBU() {
        Integer[] integers = SortTestHelper.generateRandomArray(200, 0, 100);
        MergeSortBU.sort(integers);
        assertTrue(SortTestHelper.isSorted(integers));
    }
}