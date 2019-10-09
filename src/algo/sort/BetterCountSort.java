package algo.sort;

import java.util.Arrays;

/**
 * 优化后的数组，确定数组的长度即位置,通过数组的 最大值-最小值 决定
 */

public class BetterCountSort {
    public static int[] countSort(int[] array) {
        //1.得到数列的最大值和最小值，并算出差值d
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
        //2.创建统计数组并统计对应元素的个数
        int[] countArray = new int[d + 1];
        for (int i = 0; i < array.length; i++) {
            countArray[array[i] - min]++;
        }
        //3.统计数组做变形，后面的元素等于前面的元素之和，统计之前数组每个元素对应的位置(下标)
        for (int i = 1; i < countArray.length; i++) {
            countArray[i] += countArray[i - 1];
        }
        //4.倒序遍历原始数列，从统计数组找到正确位置，输出到结果数组
        int[] sortedArray = new int[array.length];
        for (int i = array.length - 1; i >= 0; i--) {
            // 元素每个元素跟min之差对应countArray再减一得到下标对应的统计数组的值，即对应原数组每个值出现的次数，即顺序
            sortedArray[countArray[array[i] - min] - 1] = array[i];
            countArray[array[i] - min]--;   // 每次遍历都要减少次数
        }
        return sortedArray;
    }
    public static void main(String[] args) {
        int[] array = new int[]{4, 4, 6, 5, 3, 2, 8, 1, 7, 5, 6, 0, 10};
        int[] sortedArray = countSort(array);
        System.out.println(Arrays.toString(sortedArray));
    }
}
