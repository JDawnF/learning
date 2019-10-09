package algo.exe;

/**
 * @Author baichen
 * @Date 2019/10/9
 * @Description 判断链表是否有环,通过两个指针解决
 * 计算环长：
 * 当两个指针首次相遇，证明链表有环的时候，让两个指针从相遇点继续循环前进，并统计前进的循环次数，
 * 直到两个指针第2次相遇。此时，统计出来的前进次数就是环长。环长 = 每一次速度差 × 前进次数 = 前进次数。
 */
public class NodeCircle {
    /**
     * @param head 链表头结点
     * @return
     */
    public static boolean isCycle(Node head) {
        Node p1 = head;
        Node p2 = head;
        while (p1 != null && p2.next != null) { // 不是尾结点
            p1 = p1.next;
            p2 = p2.next.next;
            if (p1 == p2) {
                return true;
            }
        }
        return false;
    }

    // 链表节点
    private static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }
}
