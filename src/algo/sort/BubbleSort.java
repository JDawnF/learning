package algo.sort;

/**
 * @program: learning
 * @author: baichen
 * 冒泡排序
 * 冒泡排序只会操作相邻的两个数据。每次冒泡操作都会对相邻的两个元素进行比较，看是否满足大小关系要求。
 * 如果不满足就让它俩互换。一次冒泡会让至少一个元素移动到它应该在的位置，重复n次，就完成了n个数据的排序工作。
 **/
public class BubbleSort {
    /**
     * 加上一个标志位的Boolean类型做数据是否交换的判断
     *
     * @param array 待排序的数组
     */
    public void bubbleSort(int[] array) {
        int n = array.length;
        if (n < 1)
            return;
        // 第一层外部全数组循环
        for (int i = 0; i < n; i++) {
//          数据交换判断位
            boolean flag = false;
//            第二层从i接下来的数开始到最后的循环
            for (int j = i + 1; j < n; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    flag = true;    // 交换后表示数据已交换
                }
            }
//          没有数据交换，提前退出
            if (!flag)
                break;
        }
    }
}
