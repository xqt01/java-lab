package com.xqt.algorithm;

import java.util.*;

public class Sort {
    
    public static void main(String[] args) {
        int[] arr = {1,3,5,7,2,4,6,8};
        int n = arr.length;
        // bubbleSort(arr);
        // selectSort(arr);
        // insertSort(arr);
        // mergeSort(arr, 0, arr.length - 1);
        quickSort(arr, 0, arr.length - 1);
        // heapSort(arr, arr.length);
        // countSort(arr);
        // radixSort(arr);
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    /**
     * 冒泡排序: 两层循环. 两两比较. 大的往后放.
     * time: O(n^2)
     * space: O(1)
     */
    public static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            boolean isSorted = true;
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                    isSorted = false;
                }
            }
            if (isSorted) {
                return;
            }
        }
    }

    /**
     * 选择排序: 每次循环把大的放到第一个.
     * time: O(n^2)
     * space: O(1)
     */
    public static void selectSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int min = arr[i];
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < min) {
                    min = arr[j];
                    minIndex = j;
                }
            }
            swap(arr, minIndex, i);
        }
    }

    /**
     * 插入排序: 判断当前标的与之前的比较, 如果小于之前的元素, 则之前的元素整体右移.
     * time: 最差: O(n^2), 前面插入数组已经有序: O(n)
     * space: O(1)
     */
    public static void insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int cur = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > cur) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = cur;
        }
    }    

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    /**
     * 归并排序: 二叉树的后序遍历. 拆成最小元素后, 依次排序.
     * time: O(nlogn)
     * space: O(n)
     */
    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right)  {
            int mid = (right - left) / 2 + left;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }
    
    private static void merge(int[] arr, int left, int mid, int right) {
        int[] tmp = new int[right - left + 1];
        int i = left, j = mid + 1, index = 0;
        while (i <= mid && j <= right) {
            tmp[index++] = arr[i] <= arr[j] ? arr[i++] : arr[j++];
        }
        while (i <= mid) {
            tmp[index++] = arr[i++];
        }
        while (j <= right) {
            tmp[index++] = arr[j++];
        }
        for (int k = 0; k < tmp.length; k++) {
            arr[left + k] = tmp[k];
        }
    }

    /**
     * 快速排序
     * time: 平均O(nlogn)
     * space: O(n)
     */
    public static void quickSort(int[] arr, int left, int right) {
        if (left < right) {
            int p = partition(arr, left, right);
            quickSort(arr, left, p - 1);
            quickSort(arr, p + 1, right);
        }
    }
    
    private static int partition(int[] arr, int left, int right) {
        int i = left, j = right;
        while (i < j) {
            while (i < j && arr[j] >= arr[left]) {
                j--;
            }
            while (i < j && arr[i] <= arr[left]) {
                i++;
            }
            swap(arr, i, j);
        }
        swap(arr, i, left);
        return i;
    }

    /**
     * 堆排序: i节点父节点: (i - 1) / 2; 子节点(i + 1) / 2与(i + 2) / 2
     * time: 平均O(nlogn)
     * space: O(1)
     */
    public static void heapSort(int[] nums, int n) {
        buildheap(nums, n);
        for (int i = n - 1; i >= 0; i--) {
            swap(nums, 0, i);
            heapify(nums, i, 0);
        }
    }
    
    private static void buildheap(int[] nums, int n) {
        // n-1是最后一个节点, 取他的父节点
        int parent = (n - 2) / 2; 
        for (int i = parent; i >= 0; i--) {
            heapify(nums, n, i);
        }
    }
    
    private static void heapify(int[] nums, int n, int index) {
        if (index >= n) {
            return;
        }
        int c1 = 2 * index + 1;
        int c2 = 2 * index + 2;
        int maxIndex = index;
        if (c1 < n && nums[maxIndex] < nums[c1]) {
            maxIndex = c1;
        }
        if (c2 < n && nums[maxIndex] < nums[c2]) {
            maxIndex = c2;
        }
        // 大的往上移, 构建大顶堆
        if (index != maxIndex) {
            swap(nums, maxIndex, index);
            heapify(nums, n, maxIndex);
        }
    }

    /**
     * 计数排序, 用数组的index代表数字, value代表数量, 再遍历填入数组. 通过前缀和实现, value就代表当前元素应该出现的index位置, 就可以根据num来生成, 如果num中的元素师Object就可以这样做.
     * 适用于范围不大的非负整数
     * time: O(n + m)
     * space: O(n + m)
     */
    public static void countSort(int[] nums) {
        int maxValue = -1;
        for (int num: nums) {
            if (num > maxValue) {
                maxValue = num;
            }
        }
        int[] buckets = new int[maxValue + 1];
        for (int num: nums) {
            buckets[num]++;
        }
        int index = 0;
        for (int i = 0; i < buckets.length; i++) {
            while (buckets[i] > 0) {
                nums[index++] = i;
                buckets[i]--;
            }
        }
    }

    /**
     * 桶排序: 把数列按范围飞刀各个桶, 桶内排序. 最后合并
     * https://www.hello-algo.com/chapter_sorting/bucket_sort/
     * time: 桶比较大, 趋近于O(n); 最差就是一个桶, 看桶内的排序算法
     * space: O(n + m) 桶的数量和元素空间
     */

     /**
      * 基数排序
      */
    public static void radixSort(int[] nums) {
        //得到最大长度的数字
        int maxValue = nums[0], maxLength = 0;
        for (int num: nums) {
            if (num > maxValue) maxValue = num;
        }
        while (maxValue > 0) {
            maxValue /= 10;
            maxLength++;
        }
        //放入桶
        int[][] bucket = new int[10][nums.length];
        int[] bucketLength = new int[10];
        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
            for (int j = 0; j < nums.length; j++) {
                int dight = nums[j] / n % 10;
                bucket[dight][bucketLength[dight]] = nums[j];
                bucketLength[dight]++; //记录每个bucket已经放了多少数字, 对应下一个该放数字的index
            }
            //放入数组
            int index = 0;
            for (int k = 0; k < 10; k++) {
                for (int l = 0; l < bucketLength[k]; l++) {
                    nums[index++] = bucket[k][l];
                }
                bucketLength[k] = 0;
            }
        }
        //System.out.println(Arrays.toString(nums));
    }
}
