package Order;

import org.junit.Test;

/**
 * Created by 65401 on 2017/3/26.
 * 归并排序
 */
public class MergeSort {

    private int[] data={50,10,90,30,70,40,80,60,20};

    @Test
    public void test(){
        sort(data,0,data.length-1);

        for(int i:data){
            System.out.printf("%d ",i);
        }
    }


    /**
     * 分解数组
     * @param data:待排序数组
     * @param left：左索引
     * @param right：右索引
     */
    private void sort(int[] data,int left,int right){
        if(left<right){
            int mid=(left+right)/2;
            System.out.println(left < mid?"sort-left---L:"+left+";C:"+mid:"");
            sort(data,left,mid);//分解左数组
            System.out.println(mid + 1 < right?"sort-right---C:"+(mid + 1)+";R:"+right:"");
            sort(data,mid+1,right);//分解右数组

            merge(data,left,mid,right);
        }
    }

    /**
     * 合并数组
     * 将两个数组进行归并，归并前两个数组已经有序，归并后依然有序
     * @param data 数组对象
     * @param left 左数组的第一个元素的索引
     * @param mid 左数组的最后一个元素的索引，mid+1是右数组第一个元素的索引
     * @param right 右数组的最后一个元素的索引
     */
    private void merge(int[] data,int left,int mid,int right){
        //声明个临时数组,用来放置排序好的元素
        int[] tempArr=new int[data.length];

        int arrIndex=left;
        int midRight=mid+1;
        int temp=left;

        while(left<=mid&&midRight<=right){
            //两两比较，较小的送到临时数组中
            if(data[left]<data[midRight]){
                tempArr[arrIndex++]=data[left++];
            }else{
                tempArr[arrIndex++]=data[midRight++];
            }
        }

        //把右侧那个数据在送到临时数组中
        while(midRight<=right){
            tempArr[arrIndex++]=data[midRight++];
        }
        //把左侧那个数据在送到临时数组中
        while(left<=mid){
            tempArr[arrIndex++]=data[left++];

        }
        //每一次，把临时数组中排序好的元素送到data中
        while(temp<=right){
            data[temp]=tempArr[temp++];
        }
    }
}
