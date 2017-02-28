package Stack;

/**
 * Created by 65401 on 2017/2/28.
 *
 * 用数组实现stack
 */
public class MyArrayStack {
    private Object[] stack=new Object[5];
    private int count=0;//  计数器,查看元素个数
    /*
       数组扩容
        */
    private void resize(){
        Object[] temp=new Object[stack.length+5];
        for(int i=0;i<stack.length;i++){//  遍历旧数组
            temp[i]=stack[i];
            stack[i]=null;
        }
        stack=temp;//   将temp地址赋给stack
    }

    /**
     * push
     * @param data  新数据
     * @return  是否添加成功
     */
    public boolean push(Object data){
        if(count>=stack.length){
            this.resize();//    扩容
        }
        stack[count++]=data;
        return true;
    }

    /**
     * pop
     * @return  出stack内容
     */
    public Object pop(){
        Object data=this.stack[count-1];
        this.stack[--count]=null;
        return data;
    }

    /**
     * peek
     * @return  stack顶元素
     */
    public Object peek(){
        Object data=this.stack[count-1];
        return data;
    }

    /**
     * isEmpty
     * @return  是否为空
     */
    public boolean isEmpty(){
        if(count==0){
            return true;
        }
        return false;
    }

    /**
     * capacity
     * @return  stack的容量
     */
    public int capacity(){
        return stack.length;
    }

    /*
    test
     */
    public static void main(String[] args) {
        MyArrayStack my=new MyArrayStack();
        System.out.println(my.isEmpty());
        my.push(0);
        my.push(1);
        my.push(2);
        my.push(3);
        my.push(4);
        my.push(5);
        System.out.println(my.capacity());
        System.out.println(my.pop());
        System.out.println(my.pop());
    }
}
