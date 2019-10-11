package algo.exe;

import java.util.Arrays;

/**
 * @Author baichen
 * @Date 2019/10/11
 * @Description 找到缺失的数，
 * 一、在一个无序数组里有99个不重复的 正整数，范围从1到100……
 * 解题思路：
 * 1.创建一个哈希表，以1到100这100个整数为Key。然后遍历整个数组，每读到一个整数，就定位到哈希表中对应的Key，然后删除这个Key。(时间最优，但是空间消耗较大,时间复杂度是O(n)，空间复杂度是O(n))
 * 2.先把数组元素从小到大进行排序，然后遍历已经有序的数组，如果发现某两个相邻元素并不连续，说明缺少的就是这两个元素之间的整数。时间复杂度是O(nlogn)，空间复杂度是O(1)
 * 3.先算出1～100的累加和，然后再依次减去数组里的所有元素，最后的差值就是所缺少的整数。时间复杂度是O(n)，空间复杂度是O(1)。
 * 二、一个无序数组里有若干个正整数，范围是1～100，其中99个整数都出现了偶数次，只有1个整数出现了奇数次，如何找到这个出现奇数次的整数？
 * 解题思路：
 * 把数组里所有元素依次进行异或(相同位得0，不同位得1)运算，最后得到的就是那个缺失的整数；的时间复杂度是O(n)，空间复杂度是O(1)。
 * 三、假设一个无序数组里有若干个正整数，范围是1～100，其中有98个整数出现了 偶数次，只有2个整数出现了奇数次，如何找到这2个出现奇数次的整数？
 * 解题思路：
 * 异或加分治：
 * 把2个出现了奇数次的整数命名为A和B。遍历整个数组，然后依次做异或运算，进行异或运算的最终结果，等同于A和B进行异或运算的结果。
 * 在这个结果中，至少 会有一个二进制位是1（如果都是0，说明A和B相等，和题目不相符）。
 * 可以把原数组按照二进制的倒数第2位的不同，分成两部分，一部分的倒数第2位是0，另一部分的倒数第2位是1。
 * 时间复杂度是O(n)，空间复杂度是O(1)
 */
public class LostNum {
    // 异或加分治
    public static int[] findLostNum(int[] array) {
        //用于存储2个出现奇数次的整数
        int[] result = new int[2];
        //第1次进行整体异或运算
        int xorResult = 0;    // 得到两个数的异或结果
        for (int i = 0; i < array.length; i++) {
            xorResult ^= array[i];
        }
        //如果进行异或运算的结果为0，则说明输入的数组不符合题目要求
        if (xorResult == 0) {
            return null;
        }
        //确定2个整数的不同位，以此来做分组
        int separator = 1;
        while (0 == (xorResult & separator)) { // &:位与运算符,两个数都转为二进制，然后从高位开始比较，如果两个数都为1则为1，否则为0。
            separator <<= 1;  // 相同则左移一位,从高位开始，是第二位
        }
        System.out.println(separator);
        //第2次分组进行异或运算
        for (int i = 0; i < array.length; i++) {
            if (0 == (array[i] & separator)) {
                result[0] ^= array[i]; // 初始值是0,0与任何数异或都是本身
            } else {
                result[1] ^= array[i];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] array = {4, 1, 2, 2, 5, 1, 4, 3};
        int[] result = findLostNum(array);
        System.out.println(result[0] + "," + result[1]);
    }
}
