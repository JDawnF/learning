package algo.sort;

import java.util.Arrays;

/**
 * @Date: 2019-10-02 12:25
 * @Author: baichen
 * @Description 鸡尾酒排序算法, 第1轮从左到右，第2轮从右到左，第3轮再从左到右......不断重复
 * 代码外层的大循环控制着所有排序回合，
 * 大循环内包含2个小循环，第1个小循环从左向右比较并交换元素，第2个小循环从右
 * 向左比较并交换元素
 */
public class CocktailSort {
    public static void sort(int array[]) {
        int temp = 0;
        for (int i = 0; i < array.length; i++) {
            //有序标记，每一轮的初始值都是true
            boolean isSorted = true;
            //奇数轮，从左向右比较和交换
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    // 有元素交换，所以不是有序的，标记变为false
                    isSorted = false;
                }
            }
            if (isSorted) {
                break;
            }
            // 在偶数轮之前，将isSorted重新标记为true
            isSorted = true;
            //偶数轮，从右向左比较和交换
            for (int j = array.length - 1 - i; j > 0; j--) {
                if (array[j] < array[j - 1]) {
                    temp = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = temp;
                    isSorted=false;
                }
            }
            if (isSorted){
                break;
            }
        }
    }
    public static void main(String[] args) {
        int[] array = new int[]{1, 2, 6, 7, 3, 4, 5, 8};
        sort(array);
        System.out.println(Arrays.toString(array));
    }
}
