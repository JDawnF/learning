package algo.sort;

import java.util.Arrays;

public class HeapSort {
    /**
     * "下沉"操作，构造最大堆
     *
     * @param array       待调整的二叉堆
     * @param parentIndex 待"下沉"的父节点索引
     * @param length      堆的有效大小
     */
    public static void downAdjust(int[] array, int parentIndex, int length) {
        // temp 保存父节点值，用于最后的赋值
        int temp = array[parentIndex];
        int childIndex = 2 * parentIndex + 1;
        while (childIndex < length) {
            // 如果有右孩子，且右孩子小于左孩子的值，则定位到右孩子
            // 这里如果是小于号，则是构建最小堆，下面变成降序排序
            if (childIndex + 1 < length && array[childIndex] < array[childIndex + 1]) {
                childIndex++; // 定位到右孩子
            }
            // 如果父节点大于任何一个孩子的值，则直接跳出
            // 如果是构建最小堆则要换成 <=
            if (temp >= array[childIndex]) {
                break;
            }
            //无须真正交换，单向赋值即可
            array[parentIndex] = array[childIndex];
            parentIndex = childIndex;   // 索引位置交换
            childIndex = 2 * childIndex + 1;
        }
        array[parentIndex] = temp;
    }

    /**
     * 堆排序(升序)
     *
     * @param array 待调整的堆
     */
    public static void heapSort(int[] array) {
        // 1. 把无序数组构建成最大堆
        for (int i = (array.length - 2) / 2; i >= 0; i--) {  // 只需遍历数据一般的长度，因为子节点是父节点的2倍+1
            downAdjust(array, i, array.length);
        }
        System.out.println("构建最大堆后的数组" + Arrays.toString(array));
        // 2. 循环删除堆顶元素，移到集合尾部，调整堆产生新的堆顶
        for (int i = array.length - 1; i > 0; i--) {
            // 最后1个元素和第1个元素进行交换
            int temp = array[i];
            array[i] = array[0];
            array[0] = temp;
            // “下沉”调整最大堆
            downAdjust(array, 0, i);
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 3, 2, 6, 5, 7, 8, 9, 10, 0};
        heapSort(arr);
        System.out.println("升序排序后的数组：" + Arrays.toString(arr));
    }
}
