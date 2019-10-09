package algo.exe;

/**
 * @Author baichen
 * @Date 2019/10/9
 * @Description 获取最大公约数，有两种方法：
 * 1.辗转相除法：两个正整数a和b（a>b），它们的最大公约数等于a除以b的余数c和b之间的最大公约数，取模性能差，O(log(max(a, b)))
 * 2.更相减损法：两个正整数a和b（a>b），它们的最大公约数等于a-b的差 值c和较小数b的最大公约数,不稳定，最坏的O(max(a, b))。
 * 3.结合移位运算的更相减损法 O(log(max(a, b)))
 */
public class GreatestCommonDivisor {
    // 辗转相除法
    public static int getGreatestCommonDivisorV1(int a, int b) {
        int big = a > b ? a : b;
        int small = a < b ? a : b;
        if (big % small == 0) {
            return small;
        }
        return getGreatestCommonDivisorV1(big % small, small);
    }

    //更相减损法
    public static int getGreatestCommonDivisorV2(int a, int b) {
        if (a == b) {
            return a;
        }
        int big = a > b ? a : b;
        int small = a < b ? a : b;
        return getGreatestCommonDivisorV2(big - small, small);
    }

    //结合移位运算的更相减损法
    public static int gcd(int a, int b) {
        if (a == b) {
            return a;
        }
        if ((a & 1) == 0 && (b & 1) == 0) {     //a,b都是偶数
            return gcd(a >> 1, b >> 1) << 1; // 两个数先除以2，然后最后再乘以2
        } else if ((a & 1) == 0 && (b & 1) != 0) { // a是偶数，b是奇数
            return gcd(a >> 1, b);
        } else if ((a & 1) != 0 && (b & 1) == 0) { // a是奇数，b是偶数
            return gcd(a, b >> 1);
        } else {
            int big = a > b ? a : b;
            int small = a < b ? a : b;
            return gcd(big - small, small); // 相减后是偶数
        }
    }

    public static void main(String[] args) {
        System.out.println(getGreatestCommonDivisorV1(10, 25));
        System.out.println(getGreatestCommonDivisorV2(10, 25));
        System.out.println(gcd(10, 25));
    }
}
