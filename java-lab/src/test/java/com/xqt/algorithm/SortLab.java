package com.xqt.algorithm;

import java.util.Arrays;

/**
 * @author huanruiz
 * @since 2024/7/25
 */
public class SortLab {

    public static void main(String[] args) {
        int[] arr = {1,3,5,7,2,4,6,8};
        int n = arr.length;
        // bubbleSort(arr);


        System.out.println(Arrays.toString(arr));
    }

    /**
     * 冒泡排序: 两层循环. 两两比较. 大的往后放.
     * time: O(n^2)
     * space: O(1)
     */
    public static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int swap = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = swap;
                }
            }
        }
    }
}
