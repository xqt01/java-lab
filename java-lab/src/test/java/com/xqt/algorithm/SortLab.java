package com.xqt.algorithm;

import java.util.Arrays;

/**
 * @author huanruiz
 * @since 2024/7/25
 */
public class SortLab {

    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 2, 4, 6, 8};
        int n = arr.length;
        // bubbleSort(arr);
        // selectSort(arr);
        // insertSort(arr);
        // mergeSort(arr, 0, arr.length - 1);
        quickSort(arr, 0, arr.length - 1);

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
                    int temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
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
            for (int j = i + 1; j < arr.length; j++) {
                int minIndex = i + 1;
                if (arr[j] < arr[minIndex]) {
                    int temp = arr[minIndex];
                    arr[minIndex] = arr[j];
                    arr[j] = temp;
                }
            }
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

    /**
     * 归并排序: 二叉树的后序遍历. 拆成最小元素后, 依次排序.
     * time: O(nlogn)
     * space: O(n)
     * todo: 链表排序
     */
    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = (right - left) / 2 + left;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int i = left;
        int j = mid + 1;
        int index = 0;
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[index++] = arr[i++];
            } else {
                temp[index++] = arr[j++];
            }
        }
        while (i <= mid) {
            temp[index++] = arr[i++];
        }
        while (j <= right) {
            temp[index++] = arr[j++];
        }
        for (int k = left; k <= right; k++) {
            arr[k] = temp[k - left];
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
            while (i < j && arr[i] < arr[left]) {
                i++;
            }
            while (i < j && arr[j] > arr[left]) {
                j--;
            }
            swap(arr, i, j);
        }
        swap(arr, left, i);
        return i;
    }

    /**
     * 堆排序: i节点父节点: (i - 1) / 2; 子节点(i + 1) / 2与(i + 2) / 2
     * time: 平均O(nlogn)
     * space: O(1)
     */
    public static void heapSort(int[] arr) {
        // 最后一个节点arr.length - 1的父节点开始建堆
        for (int i = (arr.length - 2) / 2; i >= 0; i--) {
            siftDown(arr, arr.length, i);
        }
        // 把最大值放到最后
        for (int i = arr.length - 1; i > 0; i--) {
            swap(arr, 0, i);
            siftDown(arr, i, 0);
        }
    }

    private static void siftDown(int[] arr, int n, int i) {
        while (true) {
            // 取该节点和左右叶子节点最大值
            int l = 2 * i + 1;
            int r = 2 * i + 2;
            int maxIndex = i;
            if (l < n && arr[l] > arr[maxIndex]) {
                maxIndex = l;
            }
            if (r < n && arr[r] > arr[maxIndex]) {
                maxIndex = r;
            }
            if (maxIndex == i) {
                break;
            }
            // 交换两节点
            swap(arr, i, maxIndex);
            // 向叶子节点堆化
            i = maxIndex;
        }
    }


    /**
     * 交换数组的元素
     */
    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

}
