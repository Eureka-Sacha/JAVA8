package org.eureka.sorting;

/**
 * The type Quick sort.
 * 快速排序
 * 通过一趟排序将要排序的数据分割成独立的两部分，
 * 其中一部分的所有数据都比另外一部分的所有数据都要小，
 * 然后再按此方法对这两部分数据分别进行快速排序，
 * 整个排序过程可以递归进行，
 * 以此实现整个数据变成有序序列。
 *
 * @author Eureka.
 * @date 2017 -05-12 14:00:06
 * @author: 奎
 * @date: 2017 /5/12 13:59
 * @description:
 */
public class QuickSort {


    /**
     * Quick sort.
     *
     * @param array the array
     * @param lo    the lo
     * @param hi    the hi
     * @return the int
     */
    public static int quickSort(int[] array, int lo, int hi) {
        int key = array[lo];
        switching(key, array, lo, hi);
        array[hi] = key;
        return hi;
    }

    /**
     * Sort.
     *
     * @param array the array
     * @param lo    the lo
     * @param hi    the hi
     */
    public static void sort(int[] array, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        int index = quickSort(array, lo, hi);
        sort(array, lo, index - 1);
        sort(array, index + 1, hi);
    }

    public static void improvedSort(int[] array, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        int index = improvedQuickSort(array, lo, hi);
        improvedSort(array, lo, index - 1);
        improvedSort(array, index + 1, hi);
    }

    /**
     * Improved quick sort.
     * 快排优化
     * 三数取中切分
     * @param array the array
     * @param lo    the lo
     * @param hi    the hi
     * @return the int
     */
    public static int improvedQuickSort(int[] array, int lo, int hi) {
        int mid = lo + (hi - lo) / 2;
        if (array[mid] > array[hi]) {
            swap(array[mid], array[hi]);
        }
        if (array[lo] > array[hi]) {
            swap(array[lo], array[hi]);
        }
        if (array[mid] > array[lo]) {
            swap(array[mid], array[lo]);
        }
        int key = array[lo];
        switching(key, array, lo, hi);
        array[hi] = key;
        return hi;
    }

    private static void swap(int a, int b) {
        int temp = a;
        a = b;
        b = temp;
    }

    /**
     * @param key
     * @param array
     * @param lo
     * @param hi
     */
    private static void switching(int key, int[] array, int lo, int hi) {
        while (lo < hi) {
            while (array[hi] >= key && hi > lo) {
                hi--;
            }
            array[lo] = array[hi];
            while (array[lo] <= key && hi > lo) {
                lo++;
            }
            array[hi] = array[lo];
        }
    }
}
