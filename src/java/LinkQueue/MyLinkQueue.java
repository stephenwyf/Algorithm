package LinkQueue;

import org.junit.Test;

/**
 * Created by 65401 on 2017/2/28.
 * 链式单向队列
 */
public class MyLinkQueue {
    private int count=0;//   计数器

    private class Node{
        private Object data;//     节点数据
        private Node next;//    指向下个节点的地址
        //初始化全部属性的构造器
        public Node(Object data, Node next){
            this.data=data;
            this.next=next;
        }
    }
    //包含节点数据和下个节点地址
    private Node front;
    private Node rear;
    public MyLinkQueue(){
        front=new Node(null,null);
        rear=front;
        count++;
    }
    /**
     * add
     * @param data 加入链式队列的数据
     *
     */
    public void add(int data){
        Node newNode = new Node(data, null);
        rear.next = newNode;//    让尾节点的地址指向新的节点
        rear = newNode;//     以新节点作为新的尾节点
        count++;
    }

    public Object remove(){
        if(count==0){
            return null;
        }
        Node oldFront=front;
        front=front.next;//     旧的头节点地址赋给新的头节点
        oldFront.next=null;
        count--;
        return oldFront.data;
    }

    public Object peek(){
        if(count==0){
            return null;
        }
        return front.data;
    }

    @Test
    public void test(){
        this.add(4);
        this.add(5);
        this.remove();//    删除初始化front和rear时的null
        System.out.println(this.peek());
        System.out.println(this.remove());
        System.out.println(this.remove());
        System.out.println(this.remove());
    }
}
