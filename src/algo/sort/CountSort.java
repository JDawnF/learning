package algo.sort;

import java.util.Arrays;

/**
 * 计数排序即通过另外一个数组，将原来的数组的值存入，其中原来数组的值对应的是新数组的下标，而新数组的值对应的是
 * 原来数组的值对应出现的次数
 * 比较粗糙的计数排序，最大数没有考虑可能个数会比最大数小很多
 */
public class CountSort {
    public static int[] countSort(int[] array) {
        //1.得到数列的最大值
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        //2.根据数列最大值确定统计数组的长度
        int[] countArray = new int[max + 1];
        //3.遍历数列，填充统计数组
        for (int i = 0; i < array.length; i++) {
            countArray[array[i]]++;   // 计数数组下标为对应的原数组的值，然后统计原数组每个值的个数
        }
        //4.遍历统计数组，输出结果
        int index = 0;
        int[] sortedArray = new int[array.length];
        for (int i = 0; i < countArray.length; i++) {
            for (int j = 0; j < countArray[i]; j++) {   // 遍历统计数组的值对应的是原数组每个值出现的次数
                sortedArray[index++] = i;
            }
        }
        return sortedArray;
    }

    public static void main(String[] args) {
        int[] array = new int[]{4, 4, 6, 5, 3, 2, 8, 1, 7, 5, 6, 0, 10};
        int[] sortedArray = countSort(array);
        System.out.println(Arrays.toString(sortedArray));
    }
}
