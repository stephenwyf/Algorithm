
public class QuickSort {
	public static void main(String[] args) {
		int[] a={10,8,6,9,12,3,4,1};
		Quicksort(0,a.length-1,a);
		for(int i:a){
			System.out.print(i+" ");
		}
	}
	public static void Quicksort(int start,int end,int[] a){
		int f=start;//f 为0
		int l=end;//l  为a.length
		if(f>=l){// 返回void  报错
			return;
		}
		boolean flag=true; //用做排序时方向判断
		while(f!=l){//当f l重合时一次快排结束
			if(a[f]>a[l]){//交换位置
				int temp=0;
				temp=a[f];
				a[f]=a[l];
				a[l]=temp;
				flag=!flag;// 改变 移动方向
			}
			if(flag){// 此时  右》》左
				l--;
			}else f++;//  左》》右 	
		}
		f--;// 此时 f=l  让f 改为前一位
		l++;//l 改为后一位
		Quicksort(start,f,a);// 将剩下的数组分成两组 分别进行快排    0--靶子前一位
		Quicksort(l,end,a);// 靶子后一位--a.length
	}

}
