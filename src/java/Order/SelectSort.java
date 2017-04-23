package Order;

import org.junit.Test;

/**
 * Created by 65401 on 2017/3/25.
 * 选择排序法---直接排序
 */
public class SelectSort {

    private int [] data={21,30,49,39,16,9};

    @Test
    public void test(){
        for(int i=0;i<data.length-1;i++){

            for(int j=i+1;j<data.length;j++){
                int temp=0;
                if(data[i]>data[j]){
                    //前一个数据比后一个大，需要把小的放到前面
                    temp=data[j];
                    data[j]=data[i];
                    data[i]=temp;
                }
            }
        }
        for(int i: data){
            System.out.print(i+" ");
        }
    }


}
