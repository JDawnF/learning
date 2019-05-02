package algo;

/**
 * @Program: BSearch1
 * @Author: baichen
 * @Description: 二分查找变形二，在有序重复数组中，查找最后一个值等于给定值的元素
 */
public class BSearch2 {
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
            } else if (a[mid] < value) {
                low = mid + 1;
            } else {    //a[mid]==value的时
//如果此时mid是最后一个元素的话，因为是排序数组，所以肯定是最后一个相等的
//如果mid的后一个元素不等于value，那么表明mid是最后一个相等的，所以这两种情况都是返回mid
//如果上面的情况都不符合，那么表明不是最后一个相等的，所以将low右移,最后一个相等的在右边区域，继续遍历
                if ((mid==n-1)||a[mid+1]!=value) return mid;
                else low=mid+1;
            }
        }
        return -1;
    }
}
