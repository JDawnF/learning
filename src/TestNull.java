/**
 * @program: learning
 * @description: null
 * @author: baichen
 * @create: 2019-01-22 10:20
 **/
public class TestNull {
    //静态类智能调用静态变量或者方法
    private static void testMethod(){
        System.out.println("testMethod");
    }
    public static void main(String[] args) {
        //相当于将null转为TestNull类型
        ((TestNull)null).testMethod();
    }
}
