package Order;

import org.junit.Test;

/**
 * Created by 65401 on 2017/3/26.
 * Shell排序(希尔排序)
 */
public class ShellSort {

    private int [] data={47,55,10,40,15,94,5,70};

    @Test
    public void test(){
        int h=0;//      增量
        //计算增量
        while(h<=data.length/3){
            h=h*3+1;
        }

        while(h>0){
            //应用h来划分区间
            for(int i=h;i<data.length;i++){
                int temp=data[i];
                if(data[i]<data[i-h]){//   代表后面有小的值
                    int j=i-h;//    起始位置
                    //在h间隔内的元素之间互换  退格
                    while(j>=0&&data[j]>temp){
                        data[j+h]=data[j];
                        j=j-h;
                    }
                    data[j+h]=temp;//临时变量的值送给退格的那个索引的元素
                }
            }
            //下一个增量
            h=(h-1)/3;
        }

        for(int i:data){
            System.out.printf("%d ",i);
        }
    }
}
