package Stack;

import java.util.Stack;

/**
 * Created by 65401 on 2017/2/28.
 *实现一个特殊stack，在实现stack的基本功能的基础上，再实现返回stack中最小元素
 */
public class getMinStack {
    private Stack dataStack=new Stack();
    private Stack minStack=new Stack();

    public int getMin(){//   返回minStack中最小值
        return Integer.parseInt(this.minStack.peek().toString());
    }

    public void push(int data){
        if(minStack.isEmpty()){
            minStack.push(data);
        }else if(data<=this.getMin()){
            minStack.push(data);
        }
        dataStack.push(data);
    }

    public static void main(String[] args) {
        getMinStack getMin=new getMinStack();
        getMin.push(1);
        getMin.push(2);
        getMin.push(4);
        getMin.push(6);
        getMin.push(0);
        System.out.println(getMin.getMin());
    }
}
