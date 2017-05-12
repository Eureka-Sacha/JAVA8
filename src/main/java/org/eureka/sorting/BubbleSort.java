package org.eureka.sorting;

/**
 * The type Bubble sort.
 * 冒泡排序
 *
 * @author Eureka.
 * @date 2017 -05-12 13:54:53
 * @author: 奎
 * @date: 2017 /5/12 13:48
 * @description:
 */
public class BubbleSort {
    /**
     * Bubble sort.
     * 基础的冒泡排序
     *
     * @param array the array
     */
    public static void bubbleSort(int[] array) {
        int len = array.length;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = temp;
                }
            }
        }
    }


    /**
     * Improved bubble sort.
     * 优化冒泡排序, 如果第一圈循环一次替换都没进行就退出循环
     *
     * @param array the array
     */
    public static void improvedBubbleSort(int[] array) {
        int len = array.length;
        boolean flag = true;
        while (flag) {
            flag = false;
            for (int i = 0; i < len - 1; i++) {
                for (int j = 0; j < len - i - 1; j++) {
                    if (array[i] > array[i + 1]) {
                        int temp = array[i + 1];
                        array[i + 1] = array[j];
                        array[i] = temp;
                        flag = true;
                    }
                }
                len--;
            }
        }
    }
}