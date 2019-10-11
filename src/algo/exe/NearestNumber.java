package algo.exe;

import java.util.Arrays;

/**
 * @Author baichen
 * @Date 2019/10/11
 * @Description 给出一个正整数，找出这个正整数所有数字全排列的下一个数。如：12345==》12354。 12354==》12435
 * 解题思路：
 * 通过字典序算法解决：
 * 1. 从后向前查看逆序区域，找到逆序区域的前一位，也就是数字置换的边界。
 * 2. 让逆序区域的前一位和逆序区域中大于它的最小的数字交换位置。
 * 3. 把原来的逆序区域转为顺序状态 。
 */
public class NearestNumber {
    public static int[] findNearestNumber(int[] numbers) {
        //1. 从后向前查看逆序区域，找到逆序区域的前一位，也就是数字置换的边界
        int index = findTransferPoint(numbers);
        // 如果数字置换边界是0，说明整个数组已经逆序，无法得到更大的相同数字组成的整数，返回null
        if (index == 0) {
            return null;
        }
        //2.把逆序区域的前一位和逆序区域中刚刚大于它的数字交换位置
        //复制并入参，避免直接修改入参
        int[] numsCopy = Arrays.copyOf(numbers, numbers.length);
        exchangeHead(numsCopy, index);
        //3.把原来的逆序区域转为顺序
        reverse(numsCopy, index);
        return numsCopy;
    }

    /**
     * 找到逆序区域的前一位，也就是数字置换的边界
     *
     * @param numbers 待查找的数组
     * @return 返回逆序区域前一位的下标
     */
    private static int findTransferPoint(int[] numbers) {
        for (int i = numbers.length - 1; i > 0; i--) {
            if (numbers[i] > numbers[i - 1]) {
                return i;
            }
        }
        return 0;
    }

    /**
     * 交换逆序区域的前一位和逆序区域中刚刚大于它的数字,从后往前开始遍历交换
     *
     * @param numbers 待交换的数组
     * @param index   逆序区域前一位的下标
     * @return
     */
    private static int[] exchangeHead(int[] numbers, int index) {
        int head = numbers[index - 1];
        for (int i = numbers.length - 1; i > 0; i--) {
            if (head < numbers[i]) {
                numbers[index - 1] = numbers[i];
                numbers[i] = head;
                break;
            }
        }
        return numbers;
    }

    /**
     * 把原来的逆序区域转为顺序，这样才能保证是仅大于原数组的交换后的最小数组
     *
     * @param num   待转换的数组
     * @param index 逆序区域前一个数字的下标
     * @return
     */
    private static int[] reverse(int[] num, int index) {
        for (int i = index, j = num.length - 1; i < j; i++, j--) {
            int temp = num[i];
            num[i] = num[j];
            num[j] = temp;
        }
        return num;
    }

    // 输出数组
    private static void outputNumbers(int[] numbers) {
        for (int i : numbers) {
            System.out.print(i);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] numbers = {1, 2, 3, 4, 5};
        //打印12345 之后的10个全排列整数,即下一个是仅比上一个大的数组
        for (int i = 0; i < 10; i++) {
            numbers = findNearestNumber(numbers);
            outputNumbers(numbers);
        }
    }
}
