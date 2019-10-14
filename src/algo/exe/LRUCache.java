package algo.exe;

import java.util.HashMap;

/**
 * @Author baichen
 * @Date 2019/10/14
 * @Description 实现LRU算法, 类似于LinkedHashMap的实现,可以加上synchronized来实现线程安全
 */
public class LRUCache {
    private Node head;
    private Node end; // 缓存存储上限
    private int limit;
    private HashMap<String, Node> hashMap;

    public LRUCache(int limit) {
        this.limit = limit;
        hashMap = new HashMap<String, Node>();
    }

    /**
     * 每次获取值后都要先删除这个节点，然后再重新通过尾插法插入
     *
     * @param key
     * @return 对应的值
     */
    public String get(String key) {
        Node node = hashMap.get(key);
        if (node == null) {
            return null;
        }
        refreshNode(node);
        return node.value;
    }

    public void put(String key, String value) {
        Node node = hashMap.get(key);
        if (node == null) { //如果Key 不存在，则插入Key-Value
            if (hashMap.size() >= limit) {
                String oldKey = removeNode(head);  // 头节点是最少使用的值，当内存不足时先移除
                hashMap.remove(oldKey);
            }
            node = new Node(key, value);
            addNode(node);
            hashMap.put(key, node);
        } else { //如果Key 存在，则刷新Key-Value
            node.value = value;
            refreshNode(node);
        }
    }

    /**
     * 移除某个值，节点和hashmap中对应都要移除
     *
     * @param key
     */
    public void remove(String key) {
        Node node = hashMap.get(key);
        removeNode(node);
        hashMap.remove(key);
    }

    /**
     * 刷新被访问的节点位置
     *
     * @param node 被访问的节点
     */
    private void refreshNode(Node node) {
        //如果访问的是尾节点，则无须移动节点
        if (node == head) {
            return;
        }
        removeNode(node);   //移除节点
        addNode(node);  //重新插入节点
    }

    private String removeNode(Node node) {
        if (node == head && node == end) { //移除唯一的节点
            head = null;
            end = null;
        } else if (node == end) { //移除尾节点
            end = end.pre;
            end.next = null;
        } else if (node == head) {//移除头节点
            head = head.next;
            head.pre = null;
        } else { //移除中间节点,要考虑被移除节点的上个节点和下个节点
            node.pre.next = node.next;
            node.next.pre = node.pre;
        }
        return node.key;
    }

    /**
     * 尾插法，尾部插入节点
     *
     * @param node 要插入的节点
     */
    private void addNode(Node node) {
        if (end != null) {
            end.next = node;
            node.pre = end;
            node.next = null;
        }
        end = node;
        if (head == null) {
            head = node;
        }
    }

    class Node {
        Node(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public Node pre;
        public Node next;
        public String key;
        public String value;
    }

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(5);
        lruCache.put("001", " 用户1信息");
        lruCache.put("002", " 用户1信息");
        lruCache.put("003", " 用户1信息");
        lruCache.put("004", " 用户1信息");
        lruCache.put("005", " 用户1信息");
        lruCache.get("002");
        lruCache.put("004", " 用户2信息更新");
        lruCache.put("006", " 用户6信息");
        System.out.println(lruCache.get("001"));
        System.out.println(lruCache.get("006"));
    }
}
