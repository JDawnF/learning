/**
 * @program: learning
 * @author: baichen
 * 猴子摘了一些桃子，第一天吃了一半，又多吃了一个，第二天又吃了一半，
 * 又多吃一个，以后每天都是，第10天剩下一个，请问猴子一共摘了多少桃子。
 **/
public class GetTaoZi {
    public static void main(String[] args) {
        int tao = 1;//第十天 1个
        for (int i = 9; i > 0; i--) {
            tao = (tao + 1) * 2;
        }
        System.out.println("一共摘了" + tao + "个桃子");
    }
}
