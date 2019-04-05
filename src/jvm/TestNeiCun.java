package jvm;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: learning
 * @author: baichen
 * 内存泄漏时会触发java.lang.OutOfMemoryError: Java heap space
 **/
public class TestNeiCun {
    static class Key {
        Integer id;

        Key(Integer id) {
            this.id = id;
        }

        // Key这个类只重写了hashCode方法，没有重写equals方法，
        // 会导致containsKey中判断的结果为false
//        @Override
//        public int hashCode() {
//            return id.hashCode();
//        }
//        @Override
//        public boolean equals(Object o) {
//            boolean response = false;
//            if (o instanceof Key) {
//                response = (((Key)o).id).equals(this.id);
//            }
//            return response;
//        }
    }

    public static void main(String[] args) {
//        Runtime.getRuntime().gc();
        Map<Key,String> m = new HashMap<Key,String>();
        while(true) {
            for(int i=0;i<10000;i++) {
                // 如果没有重写hashCode和equals的话，这里都是返回false
                if(!m.containsKey(i)) {
                    m.put(new Key(i), "Number:" + i);
                }
            }
            System.out.println(m.size());
        }
    }
}
