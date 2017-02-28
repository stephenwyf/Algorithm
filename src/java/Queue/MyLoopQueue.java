package Queue;

import org.junit.Test;

/**
 * Created by 65401 on 2017/2/28.
 * 用array实现循环queue
 */
public class MyLoopQueue {
    private int capacity=3;//  队列长度
    private Object[] queue=new Object[capacity];
    private int front=0;//  队首
    private int rear=0;//   队尾

    /**
     * add
     * @param data  新的元素
     */
    public void add(Object data){
        queue[rear++]=data;
        rear=rear==capacity?0:rear;//   *判断是刚好走完循环
    }

    /**
     * remove
     * @return  弹出list的值
     */
    public Object remove(){
        Object temp=queue[front];
        queue[front++]=null;
        front=front==capacity?0:front;//    *判断是刚好走完循环
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
        this.add(3);//  达到循环
        this.add(4);//  成为队首
        this.add(5);
        System.out.println(this.peek());
        System.out.println(this.remove());
        System.out.println(this.remove());
        System.out.println(this.remove());
        System.out.println(this.remove());
    }
}
