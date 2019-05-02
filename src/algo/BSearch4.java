package algo;

/**
 * @Program: BSearch1
 * @Author: baichen
 * @Description: 二分查找变形四，在有序重复数组中，查找最后一个小于等于给定值的元素。
 */
public class BSearch4 {
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
            if (a[mid] > value) {
                high=mid-1;
            } else {
                // 查看是否为最后一个小于等于value的数
                if ((mid == n-1) || a[mid +1] > value) return mid;
                else low = mid +1;
            }
        }
        return -1;
    }
}
