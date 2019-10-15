package algo.exe;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @Date: 2019-10-15 20:45
 * @Author: baichen
 * @Description 拆分红包算法，通过二倍均值法实现
 * 假设剩余红包金额为m元，剩余人数为n，那么有如下公式：
 * 每次抢到的金额 = 随机区间 [0.01，m /n × 2 - 0.01]元
 */
public class DivideRedPackage {
    /**
     * 拆分红包
     *
     * @param totalAmount    总金额（以分为单位）
     * @param totalPeopleNum 总人数
     * @return
     */
    public static List<Integer> divideRedPackage(Integer totalAmount, Integer totalPeopleNum) {
        List<Integer> amountList = new ArrayList<>();
        Integer restAmt = totalAmount;
        Integer restPeopleNum = totalPeopleNum;
        Random random = new Random();
        for (int i = 0; i < totalPeopleNum - 1; i++) {
            //随机范围：[1，剩余人均金额的2倍-1] 分
            int amount = random.nextInt(restAmt / restPeopleNum * 2 - 1) + 1;
            restAmt -= amount;
            restPeopleNum--;
            amountList.add(amount);
        }
        amountList.add(restAmt);
        return amountList;
    }

    public static void main(String[] args) {
        List<Integer> amountList = divideRedPackage(1000, 10);
        for (Integer amount : amountList) {
            System.out.println(" 抢到金额：" + new BigDecimal(amount).
                    divide(new BigDecimal(100)));
        }
    }
}
