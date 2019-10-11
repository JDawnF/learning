package algo.exe;

/**
 * @Author baichen
 * @Date 2019/10/11
 * @Description 给出一个整数，从该整数中去掉k个数字，要求剩下的数字形成的新整数尽可能 小。应该如何选取被去掉的
 * 数字？ 其中整数的长度大于或等于k，给出的整数的大小可以超过long类型的数字范 围。
 * 解题思路：
 * 把原整数的所有数字从左到右进行比较， 如果发现某一位数字大于它右面的数字，那么在删除该数字后，
 * 必然会使该数位的 值降低，因为右面比它小的数字顶替了它的位置。即从左往右，即从高位开始比较，先删除左边大于右边的那个数字
 */
public class RemoveKDigits {
    /**
     * 删除整数的k个数字，获得删除后的最小值
     *
     * @param num 原整数
     * @param k   要删除的数字个数
     * @return
     */
    public static String removeKDigits(String num, int k) {
        //新整数的最终长度 = 原整数长度-k
        int newLength = num.length() - k;
        //创建一个类似栈的字符数组，用于接收所有的数字
        char[] stack = new char[num.length()];
        int top = 0;  // 模拟的栈顶
        for (int i = 0; i < num.length(); i++) { // 遍历数字字符串
            //获取当前数字
            char c = num.charAt(i);
            //当栈顶数字大于遍历到的当前数字时，栈顶数字出栈（相当于删除数字）
            while (top > 0 && stack[top - 1] > c && k > 0) {  // 左边大于右边的数字时，就删掉
                top -= 1;
                k -= 1;
            }
            //遍历到的当前数字入栈
            stack[top++] = c;
        }
        // 找到栈中第1个非零数字的位置，以此构建新的整数字符串
        int offset = 0;
        while (offset < newLength && stack[offset] == '0') {
            offset++; // 统计第一个非0数字的位置
        }
        return offset == newLength ? "0" : new String(stack, offset, newLength - offset);
    }

    public static void main(String[] args) {
        System.out.println(removeKDigits("1593212", 3));
        System.out.println(removeKDigits("30200", 1));
        System.out.println(removeKDigits("10", 2));
        System.out.println(removeKDigits("541270936", 3));
    }
}
