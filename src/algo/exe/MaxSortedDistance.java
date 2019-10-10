package algo.exe;

import java.util.Arrays;

/**
 * @Author baichen
 * @Date 2019/10/10
 * @Description 有一个无序整型数组，如何求出该数组排序后的任意两个相邻元素的最大差 值？要求时间和空间复杂度尽可能低。
 * 解题思路：
 * 1.计数排序
 * 对于间隔相差较大的数性能很低，不推荐使用
 * 2.桶排序
 * 1. 利用桶排序的思想，根据原数组的长度n，创建出n个桶，每一个桶代表一个 区间范围。其中第1个桶从原数组的最小值min开始，区间跨度是（max-min）/（n- 1）。
 * 2. 遍历原数组，把原数组每一个元素插入到对应的桶中，记录每一个桶的最大 和最小值。
 * 3. 遍历所有的桶，统计出每一个桶的最大值，和这个桶右侧非空桶的最小值的 差，数值最大的差即为原数组排序后的相邻最大差值。
 */
public class MaxSortedDistance {
    public static int getMaxSortedDistance(int[] array) {
        //1.得到数列的最大值和最小值
        int max = array[0];
        int min = array[0];
        for (int i = 0; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
            if (array[i] < min) {
                min = array[i];
            }
        }
        int d = max - min;
        //如果max 和min 相等，说明数组所有元素都相等，返回0
        if (d == 0) {
            return 0;
        }
        //2.初始化桶
        int bucketNum = array.length;
        Bucket[] buckets = new Bucket[bucketNum];
        for (int i = 0; i < bucketNum; i++) {
            buckets[i] = new Bucket();
        }
        //3.遍历原始数组，确定每个桶的最大最小值
        for (int i = 0; i < array.length; i++) {
            //确定数组元素所归属的桶下标,减去min是为了保证最小的值在第一位，节省空间
            int index = ((array[i] - min) * (bucketNum - 1) / d);
            if (buckets[index].min == null || buckets[index].min > array[i]) {
                buckets[index].min = array[i];
            }
            if (buckets[index].max == null || buckets[index].max < array[i]) {
                buckets[index].max = array[i];
            }
        }
        //4.遍历桶，找到最大差值
        int leftMax = buckets[0].max;  // 临时变量，在每一轮迭代时存储当前左侧桶的最大值。而两个桶之间的差值，则是 buckets[i].minleftMax。
        int maxDistance = 0;       // 最大距离
        for (int i = 1; i < buckets.length; i++) {
            if (buckets[i].min == null) {
                continue;
            }
            if (buckets[i].min - leftMax > maxDistance) {
                maxDistance = buckets[i].min - leftMax;
            }
            leftMax = buckets[i].max; // 每次都记录最大值
        }
        return maxDistance;
    }

    private static class Bucket {
        Integer max;
        Integer min;
    }

    public static void main(String[] args) {
        int[] array = new int[]{2, 6, 3, 4, 5, 10, 9};
        System.out.println(getMaxSortedDistance(array));
    }
}
