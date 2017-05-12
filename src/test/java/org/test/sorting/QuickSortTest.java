package org.test.sorting;

import org.eureka.sorting.QuickSort;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

/**
 * @author: 奎
 * @date: 2017/5/12 14:25
 * @description:
 */
public class QuickSortTest {
    @Test
    public void improvedSort() throws Exception {
        QuickSort.improvedSort(array,lo,hi);
    }


    private static int[] array = new int[10000];
    private static int lo = 0;
    private static int hi = array.length - 1;
    private long start = 0L;

    @Before
    public void setUp() throws Exception {
        for (int i = 0; i < array.length; i++) {
            Random random = new Random();
            array[i] = random.nextInt(Integer.MAX_VALUE);
        }
        start = System.currentTimeMillis();
    }

    @After
    public void tearDown() throws Exception {
        for (int i : array) {
            System.out.println(i);
        }
        System.out.println("times=" + (System.currentTimeMillis() - start));
    }

    @Test
    public void quickSort() throws Exception {
        QuickSort.quickSort(array, lo, hi);
    }
    @Test
    public void improvedQuickSort() throws Exception {
        QuickSort.improvedQuickSort(array, lo, hi);
    }
    @Test
    public void sort() throws Exception {
        QuickSort.sort(array, lo, hi);
    }

}