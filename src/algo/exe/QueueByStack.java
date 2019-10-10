package algo.exe;

import java.util.Stack;

/**
 * @Author baichen
 * @Date 2019/10/10
 * @Description 通过两个栈实现一个队列
 */
public class QueueByStack {
    private Stack<Integer> stackA=new Stack<>();
    private Stack<Integer> stackB=new Stack<>();

    private void enQueue(int element){
        stackA.push(element);
    }

    private Integer deQueue(){
        if (stackA.isEmpty()){
            if (stackB.isEmpty()){
                return null;
            }
            transfer();
        }
        return stackB.pop();
    }

    private void transfer(){
        while (!stackA.isEmpty()){
            stackB.push(stackA.pop());
        }
    }
}
