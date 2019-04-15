package algo.sort;

/**
 * @program: learning
 * @author: baichen
 * 快速排序
 **/
public class QuickSort {
    public static void quickSort3(int arr[], int _left, int _right) {

        int left = _left;
        int right = _right;
        int temp = 0;
        if (left <= right) {   //待排序的元素至少有两个的情况
            temp = arr[left];  //待排序的第一个元素作为基准元素
            while (left != right) {   //从左右两边交替扫描，直到left = right
                while (right > left && arr[right] >= temp) {
                    right--;        //从右往左扫描，找到第一个比基准元素小的元素
                }
                // 不满足条件说明有需要交换的数，此时要进行交换
                arr[left] = arr[right];
                while (left < right && arr[left] <= temp) {
                    left++;         //从左往右扫描，找到第一个比基准元素大的元素
                }
                // 不满足条件说明有需要交换的数，此时要进行交换
                arr[right] = arr[left];
            }
            arr[right] = temp;    //基准元素归位
            quickSort3(arr, _left, left - 1);  //对基准元素左边的元素进行递归排序
            quickSort3(arr, right + 1, _right);  //对基准元素右边的进行递归排序

        }
    }
}
