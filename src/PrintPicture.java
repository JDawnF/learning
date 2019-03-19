/**
 * @program: learning
 * @author: baichen
 **/
public class PrintPicture {
    //    1.打印等腰三角形
    public static void printTriangle() {
        // 行数
        for (int i = 1; i <= 5; i++) {
            //空格数
            for (int j = 5 - i - 1; j >= 0; j--) {
                System.out.print(" ");
            }
            //* 数,每一行都会遍历一遍*的总数
            for (int k = 1; k <= 2 * i - 1; k++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    // 打印打印直角三角形
    public static void printTriangle2() {
        for (int i = 1; i <= 5; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    // 打印打印实心菱形
    public static void printRhombic(int n) {
        for (int i = 1; i < n; i++) {
            for (int j = n - i - 1; j >= 0; j--) {
                System.out.print(" ");
            }
            for (int k = 1; k <= 2 * i - 1; k++) {
                System.out.print("*");
            }
            System.out.println();
        }
        for (int i = n; i >= 1; i--) {
            for (int j = n - i - 1; j >= 0; j--) {
                System.out.print(" ");
            }
            for (int k = 1; k <= 2 * i - 1; k++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        printRhombic(5);
    }
}
