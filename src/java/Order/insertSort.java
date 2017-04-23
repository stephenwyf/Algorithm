package Order;

import org.junit.Test;

/**
 * Created by 65401 on 2017/3/26.
 * 直接插入排序
 * 折半插入排序
 */
public class insertSort {
    int[] num = {49,38,10,97,76,13,27};

    //直接插入排序
    @Test
    public void InsertSort(){
        for(int i=0;i<num.length;i++){
            int index=-1;//     较大元素的索引
            for(int j=0;j<i;j++){
                //找到较小的之后，立刻退出循环
                if(num[i]<num[j]){
                    index=j;
                    break;
                }
            }
            //进行倒退，并且插入
            if(index!=-1){
                int temp=num[i];//较小的值放到临时变量上
                moveBack(i,index);//退元素
                num[index]=temp;//把小的赋值到第一个后退元素的那个位置上
            }
        }

        for(int i:num){
            System.out.printf("%d ",i);
        }
    }

    //二分插入排序
    @Test
    public void BinaryInsertSort(){
        for(int i=0;i<num.length-1;i++){
            if(num[i+1]<num[i]){
                //较小的值放在临时变量中
                int temp=num[i+1];

                int low=0;
                int high=i;
                while(low<=high){
                    int mid=(low+high)/2;
                    //看看在哪个数值的范围，挨个比较其值
                    if(num[mid]<temp){
                        low=mid+1;
                    }else{
                        high=mid-1;
                    }
                }
                //找到要插入的位置
                //退元素
                this.moveBack(i+1,low);
                num[low]=temp;
            }
        }

        for(int i:num){
            System.out.printf("%d ",i);
        }
    }



    /*
    退元素
    from:较小的元素索引
    to:较大的元素索引
     */
    private void moveBack(int from,int to){
        //退值，退到较小的那个点，之后就退出循环
        for(int i=from;i>to;i--){
          num[i]=num[i-1];
        }
    }
}
