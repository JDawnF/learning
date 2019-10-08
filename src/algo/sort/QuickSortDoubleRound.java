package algo.sort;

import java.util.Arrays;

public class QuickSortDoubleRound {

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
     * 分治(双边循环法)
     *
     * @param arr        待交换的数组
     * @param startIndex 起始下标
     * @param endIndex   结束下标
     * @return 指针重合的下标, 即基准元素的位置
     */
    private static int partition(int[] arr, int startIndex, int endIndex) {
        // 取第1个位置（也可以选择随机位置）的元素作为基准元素
        int pivot = arr[startIndex];
        int left = startIndex;
        int right = endIndex;
        while (left != right) {
            //控制right 指针比较并左移,左移表示移动前这个元素不用交换
            while (left < right && arr[right] > pivot) {
                right--;
            }
            //控制left指针比较并右移，右移表示移动前这个元素不用交换
            while (left < right && arr[left] <= pivot) {
                left++;
            }
            //交换left和right 指针所指向的元素
            if (left < right) {
                int temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
            }
        }
        //pivot 和指针重合点交换
        arr[startIndex] = arr[left];   // 基准元素与指针重合所指的元素交换
        arr[left] = pivot;              // 指针重合所指的元素作为新的基准元素
        return left;
    }

    public static void main(String[] args) {
        int[] array = new int[]{4, 4, 6, 5, 3, 2, 8, 1};
        long time1 = System.currentTimeMillis();
        quickSort(array, 0, array.length - 1);
        long time2 = System.currentTimeMillis();
        long cout = time2 - time1;
        System.out.println(Arrays.toString(array) + cout);
    }
}
