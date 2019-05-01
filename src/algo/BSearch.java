package algo;

/**
 * @Program: BSearch
 * @Author: baichen
 * @Description: 二分查找
 */
public class BSearch {
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
            if (a[mid] == value) {
                return mid;
            } else if (a[mid] < value) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }
}