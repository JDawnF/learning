package algo;

/**
 * @Program: BSearch1
 * @Author: baichen
 * @Description: 二分查找变形三，在有序重复数组中，查找第一个大于等于给定值的元素。
 */
public class BSearch3 {
    /**
     * @param a     待查找数组
     * @param n     数组长度
     * @param value 待查找的值
     * @return
     */
    public int bSearch(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;
        // 注意循环退出的条件是low <= high，而不是low < high，因为等于也成立
        while (low <= high) {
//            int mid = low + (high - low) / 2;
            int mid = low + ((high - low) >> 1);
            if (a[mid] >= value) {  // 查看是否为第一个大于value的数
                if ((mid == 0) || a[mid - 1] < value) return mid;
                else high = mid - 1;
            } else {
// 如果a[mid]小于要查找的值value，那要查找的值肯定在[mid+1, high]之间，所以low=mid+1。
                low = mid + 1;
            }
        }
        return -1;
    }
}
