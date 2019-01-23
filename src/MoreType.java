/**
 * @program: learning
 * @description: 多态
 * @author: baichen
 * @create: 2019-01-23 09:12
 **/
public class MoreType {
    private String baseName = "base";

    public MoreType() {
        callName();
    }

    public void callName() {
        System.out.println(baseName);
    }

    static class Sub extends MoreType {
        private String baseName = "sub";

        public void callName() {
            System.out.println(baseName);
        }
    }

    public static void main(String[] args) {
//这是多态的一种表现形式，声明是Base,实现是Sub类， 理解为 b 编译时表现为Base类特性，
// 运行时表现为Sub类特性。
//        String s;
//        System.out.println(s);
        MoreType b=new Sub();
    }
}
