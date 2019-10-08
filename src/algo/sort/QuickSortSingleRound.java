package algo.sort;

import java.util.Arrays;

public class QuickSortSingleRound {
    /**
     * @param arr        待排序的数组
     * @param startIndex 起始下标
     * @param endIndex   结束下标
     */
    public static void quickSort(int[] arr, int startIndex, int endIndex) {
        // 递归结束条件：startIndex大于或等于endIndex时
        if (startIndex >= endIndex)
            return;
        // 得到基准元素位置
        int pivotIndex = partition(arr, startIndex, endIndex);
        // 根据基准元素，分成两部分进行递归排序
        quickSort(arr, startIndex, pivotIndex - 1);
        quickSort(arr, pivotIndex + 1, endIndex);
    }

    /**
     * 分治(单边循环法)
     *
     * @param arr        待交换的数组
     * @param startIndex 起始下标
     * @param endIndex   结束下标
     * @return 指针重合的下标, 即基准元素的位置
     */
    private static int partition(int[] arr, int startIndex, int endIndex) {
        // 取第1个位置（也可以选择随机位置）的元素作为基准元素
        int pivot = arr[startIndex];
        int mark = startIndex;
        for (int i = startIndex + 1; i <= endIndex; i++) {
            if (arr[i] < pivot) {
                mark++;
                int temp = arr[mark];
                arr[mark] = arr[i];
                arr[i] = temp;
            }
        }
        arr[startIndex] = arr[mark];
        arr[mark] = pivot;
        return mark;
    }

    public static void main(String[] args) {
        int[] array = new int[]{14, 24, 6, 33, 4, 6, 15, 3, 4, 24, 6, 33, 1234, 6, 15, 133, 3132, 14, 24, 6, 33, 4, 6, 15, 3, 4, 24, 6, 33, 1234, 6, 15, 133, 3132, 9, 34, 226, 5, 3, 12, 8, 38, 55, 53, 29, 34, 226, 5, 3, 12, 8, 8, 5, 3, 12, 8, 6, 2, 9, 4, 6, 5, 3, 12, 8, 8, 5, 3, 12, 8, 1};
        long time1 = System.currentTimeMillis();
        quickSort(array, 0, array.length - 1);
        long time2 = System.currentTimeMillis();
        long cout = time2 - time1;
        System.out.println("单边循环"+ time1+"cc"+time2+cout);
    }
}
