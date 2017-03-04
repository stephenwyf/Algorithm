package LinkQueue;

import org.junit.Test;

/**
 * Created by 65401 on 2017/2/28.
 * 链式的双向队列
 */
public class DoubleLinkedList {
    private class Node{//    做一个结点内部类
        Object data;//  数据
        Node next;//    后继指针
        Node previous;//       前驱指针
        Node(Object data){
            this.data=data;
        }
    }
    private Node front;//   首结点
    private Node rear;//    尾结点
    /*
    peekHead
     */
    private Object peekHead(){
        if(front!=null){
            return front.data;
        }
        return null;
    }
    /*
    peekLast
     */
    private Object peekLast(){
        if(rear!=null){
            return rear.data;
        }
        return null;
    }
    /*
    队首插入
     */
    private void insertHead(Object data){
        Node newNode=new Node(data);
        if(front==null){//  当队首为空时，第一次插入的新结点为尾结点
            rear=newNode;
        }else{
            front.previous=newNode;//   旧结点的上一个结点为新结点
        }
        newNode.next=front;//   新结点的后一个结点为旧结点
        front=newNode;//    新结点成为新的首结点
    }
    /*
    队尾插入(与队首插入相反)
     */
    private void insertLast(Object data){
        Node newNode=new Node(data);
        if(front==null){//      当队首为空时，第一次插入的新结点为首结点
            front=newNode;
        }else{
            rear.next=newNode;
        }
        newNode.previous=rear;
        rear=newNode;
    }
    /*
    队首删除
     */
    private Object deleteHead(){
        if(front==null) {
            return null;
        }
        Node temp=front;
        front=front.next;//     将首结点的地址赋给下一个结点
        front.previous=null;
        return temp.data;
    }
    /*
    队尾删除(与队首删除相反)
     */
    private Object deleteLast(){
        if(front==null){
            return null;
        }
        Node temp=rear;
        rear=rear.previous;
        rear.next=null;
        return temp.data;
    }

    @Test
    public void test(){
        this.insertLast(10);
        this.insertLast(1);
        this.insertHead(5);
        this.insertHead(6);
        this.deleteHead();
        this.deleteHead();
        this.deleteLast();
        System.out.println(this.peekHead());
        System.out.println(this.peekLast());
    }
}
