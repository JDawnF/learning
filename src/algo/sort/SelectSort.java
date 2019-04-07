package algo.sort;

/**
 * @program: learning
 * @author: baichen
 * 选择排序
 * 选择排序和插入排序类似，也是区分已排序区间和未排序区间。
 * 但是选择排序每次是选择未排序区间中最小的元素，将其添加到已排序列的末尾。
 * 而选择排序是固定选择第一个未排序的元素插入到已排序序列的合适位置
 * 第0个逐步和后面全部的比,比完0位置就得到最小的数,紧接着再从1位置对比后面的元素,
 * 以此类推,逐步得到从小到大的值.
 **/
public class SelectSort {
    public static void selectionSort(int[] array) {
        int n = array.length;
        if (n <= 1) return;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;   // 最小元素对应的索引
            for (int j = i + 1; j < n; j++) {
                if (array[j] < array[minIndex])
                    minIndex = j;   // 如果有更小的即交换索引
            }
            // 交换数据
            int temp = array[i];
            array[i] = array[minIndex];
            array[minIndex] = temp;
        }
    }
}
