package Order;

import org.junit.Test;

/**
 * Created by 65401 on 2017/3/26.
 * 堆排序
 */
public class heapSort {


    /*
        *        9
        *      /   \
        *    79    46
        *   / \   /
        * 30  58 49
        */
    private int [] data={9,79,46,30,58,49};

    //构建大顶堆
    private void buildMaxHeap(int lastIndex){
        //通过最后一个节点索引找到父节点
        //找到父亲索引，在通过递减过程，挨个访问儿子
        for(int i=(lastIndex-1)/2;i>=0;i--){
            int father=i;
            int bigIndex=i*2+1;

            //lastIndex是右节点
            if(bigIndex<lastIndex){
                //比较左右子节点的大小
                if(data[bigIndex]<data[lastIndex]){
                    bigIndex++;//       记录右节点
                }
                //否则记录左节点
            }

            //较大的索引号的元素再跟父亲比
            if(data[bigIndex]>data[father]){
                int tmp=data[bigIndex];
                data[bigIndex]=data[father];
                data[father]=tmp;
            }
        }
    }


    @Test
    public  void test() {
        for(int i=0;i<data.length-1;i++){
            this.buildMaxHeap(data.length-1-i);//大顶堆

            //把每次大的元素都放到相对最后的那个索引位置，使其数组是按从小到大排列
            int temp=data[0];
            data[0]=data[data.length-1-i];
            data[data.length-1-i]=temp;
        }
        for(int i:data){
            System.out.printf("%d ",i);
        }
    }

}
