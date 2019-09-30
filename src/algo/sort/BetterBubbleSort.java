package algo.sort;

import java.util.Arrays;

/**
 * 经过优化的冒泡排序,通过设定一个两个数交换的标志和记录最后一次交换的位置，因为可能有有序区域的存在，避免不必要的遍历
 */

public class BetterBubbleSort {
    public static void sort(int array[]) {
        //记录最后一次交换的位置
        int lastExchangeIndex = 0;
        //无序数列的边界，每次比较只需要比到这里为止
        int sortBorder = array.length - 1;
        for (int i = 0; i < array.length - 1; i++) {
            //有序标记，每一轮的初始值都是true
            boolean isSorted = true;
            for (int j = 0; j < sortBorder; j++) {
                int temp = 0; // 中间变量，先赋值为0
                if (array[j] > array[j + 1]) {
                    temp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = temp;
                    // 因为有元素进行交换，所以不是有序的，标记变为false
                    isSorted = false;
                    // 更新为最后一次交换元素的位置
                    lastExchangeIndex = j;
                }
            }
            sortBorder = lastExchangeIndex;
            if (isSorted) {
                break;      // 如果已经是排好序则跳出循环
            }
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{1, 2, 6, 7, 3, 4, 5, 8};
        sort(array);
        System.out.println(Arrays.toString(array));
    }
}
