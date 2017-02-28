package Queue;

import org.junit.Test;

/**
 * Created by 65401 on 2017/2/28.
 * 用数组实现queue       顺序队列
 */
public class MyArrayQueue {
    private Object[] queue=new Object[10];
    private int front=0;//  队首
    private int rear=0;//   队尾

    /**
     * add
     * @param data  新的元素
     */
    public void add(Object data){
        queue[rear++]=data;
    }

    /**
     * remove
     * @return  弹出queue的值
     */
    public Object remove(){
        Object temp=queue[front];
        queue[front++]=null;

        return temp;
    }

    /**
     * peek
     * @return  queue中队首的值
     */
    public Object peek(){
        return queue[front];
    }

    @Test
    public void test(){
        this.add(1);
        this.add(2);
        this.add(3);
        System.out.println(this.peek());
        System.out.println(this.remove());
        System.out.println(this.remove());
        System.out.println(this.remove());
        System.out.println(this.remove());
    }
}
