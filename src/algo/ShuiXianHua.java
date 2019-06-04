package algo;

/**
 * @Program: ShuiXianHua
 * @Author: baichen
 * @Description: 水仙花
 */
public class ShuiXianHua {
    public static void main(String[] args) {
        int n, a, b, c;
        for (n = 100; n < 999; n++) {
            a = n % 10;  // 个位
            b = n / 10 % 10;   // 十位
            c = n / 100 % 10;  // 百位
            if (n == a * a * a + b * b * b + c * c * c)
                System.out.println(n);
        }
    }
}
