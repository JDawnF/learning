package algo.sort;

/**
 * @program: learning
 * @author: baichen
 * 插入排序
 * 将数组中的数据分为两个区间，已排序区间和未排序区间。初始已排序区间只有一个元素，就是数组的第一个元素。
 * 插入算法的核心思想是取未排序区间中的元素，在已排序区间中找到合适的插入位置将其插入，
 * 并保证已排序区间数据一直有序。重复这个过程，直到未排序区间中元素为空，算法结束。
 * 首先对数组的前两个数据进行从小到大的排序。
 * 接着将第三个数据与排好序的两个数据比较，将第三个数据插入合适的位置。
 * 然后将第四个数据插入到已排好序的前3个数据中。
 * 其实就是每次都是一个区域内的排序比较大小
 **/
public class InsertSort {
    public static void insertionSort(int[] array) {
        int n = array.length;
        if (n < 1)
            return;
        // 外循环，从第二个数开始
        for (int i = 1; i < n; i++) {
            int temp = array[i];
            int j = i - 1;
            // 每次的区间循环，第一次从0开始，temp与前面的值比较,分割为一个个区间
            while (j >= 0 && temp < array[j]) {
                array[j + 1] = array[j];    // 数据移动
                j--;
            }
            // 插入数据
            array[j + 1] = temp;
        }
    }

    public static void main(String[] args) {
        int[] arr=new int[]{2,3,1,7,5};
        insertionSort(arr);
    }
}
