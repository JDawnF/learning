package algo.exe;

import java.util.Stack;

/**
 * @Author baichen
 * @Date 2019/10/9
 * @Description 获取栈中的最小元素，通过一个主栈和辅助栈解决，要维持好辅助栈
 */
public class MinStack {
    private Stack<Integer> mainStack = new Stack<>();
    private Stack<Integer> minStack = new Stack<>();

    /**
     * 入栈
     *
     * @param element 入栈元素
     */
    public void push(int element) {
        mainStack.push(element);
        //如果辅助栈为空，或者新元素小于或等于辅助栈栈顶，则将新元素压入辅助栈
        if (minStack == null || element <= minStack.peek()) {
            minStack.push(element);
        }
    }

    /**
     * 出栈操作
     *
     * @return 返回出栈元素
     */
    public Integer pop() {
        //如果出栈元素和辅助栈栈顶元素值相等，辅助栈也要出栈，维持好辅助栈
        if (mainStack.peek().equals(minStack.peek())) {
            minStack.pop();
        }
        return mainStack.pop();
    }

    // 获取最小元素
    public int getMin() throws Exception {
        if (mainStack.isEmpty()) {
            throw new Exception("stack is empty");
        }
        return minStack.peek();
    }
}
