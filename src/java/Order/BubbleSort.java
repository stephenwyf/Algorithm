package Order;

/**
 * Created by 65401 on 2017/3/23.
 * 冒泡排序
 */
public class BubbleSort {
    private int[] data={9,16,27,23,30,49,21,35};

    private void bubbleSort(){
        for(int i=0;i<data.length;i++){
            for(int j=0;j<data.length-1-i;j++){
                if(data[j]-data[j+1]>0){
                    int temp=data[j+1];
                    data[j + 1] = data[j];
                    data[j] = temp;
                }
            }
        }
    }

    private void print(){
        for(int i=0;i<data.length;i++){
            System.out.print(data[i]+" ");
        }
    }

    public static void main(String[] args) {
        BubbleSort b=new BubbleSort();
        b.bubbleSort();
        b.print();
    }
}
