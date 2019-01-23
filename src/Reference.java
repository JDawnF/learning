/**
 * @program: design
 * @description: 随便测试
 * @author: baichen
 * @create: 2019-01-22 09:41
 **/
class Value {
    public int i = 15;
}

public class Reference {
    public static void main(String argv[]) {
        Reference t = new Reference();
        t.first();
    }

    public void first() {
        int i = 5;
        Value v = new Value();
        v.i = 25;
        second(v, i);
        System.out.println(v.i);    //实际上v还是原来的地址，所以v.i=20
    }

    public void second(Value v, int i) {
        i = 0;
        v.i = 20;
        Value val = new Value();
        v = val;        //这个时候v和val这两个实例指向同个地址，所以v.i=val.i=15
        System.out.println(v.i + " " + i);
    }
}
