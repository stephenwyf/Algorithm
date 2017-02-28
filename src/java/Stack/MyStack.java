package Stack;

import java.util.Stack;

/**
 * Created by 65401 on 2017/2/28.
 *
 * 使用JDK自带stack
 */
public class MyStack {
    public static void main(String[] args) {
        Stack s = new Stack();
        s.push("0");
        s.push(1);
        s.push("two");
        System.out.println(s.isEmpty());
        System.out.println(s.peek());
        System.out.println(s.capacity());
        System.out.println(s.pop());
        System.out.println(s.capacity());
        System.out.println(s.pop());
    }
}
