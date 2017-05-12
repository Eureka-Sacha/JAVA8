package org.eureka.sorting;

/**
 * The type Insert sort.
 *
 * @author Eureka.
 * @date 2017 -05-12 15:01:15
 * @author: 奎
 * @date: 2017 /5/12 15:00
 * @description:
 */
public class InsertSort {
    /**
     * Insert sort.
     *
     * @param array the array
     */
    public static void insertSort(int[] array) {
        for (int i = 1, len = array.length; i < len; i++) {
            if (array[i] < array[i - 1]) {
                int temp = array[i];
                int j;
                for (j = i - 1; j >= 0 && temp < array[j]; j--) {
                    array[j + 1] = array[j];
                }
                array[j + 1] = temp;
            }

        }
    }
}
