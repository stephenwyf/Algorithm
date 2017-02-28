import java.util.*;
public class BinarySearch {//手写二分查找法
	public static void main(String[] args) {
		int[] a={10,9,8,7,6,5,4,3,2,1,0};
		Arrays.sort(a);//排序
		System.out.println(binarySearch(1,a));
	}
	public static int binarySearch(int key,int[] a){//定义 要查找的数key
		for(int f=0,l=a.length-1;f<=l;){//f---数组第一个   l---数组最后一个   f<=l---循环条件
			int mid=(f+l)/2;// 定义一个数组中间数 mid
			if(key>a[mid]){// 当key>mid f移至mid后一位
				f=mid+1;
			}else if(key<a[mid]){//当key<mid l移至mid前一位
				l=mid-1;
			}else return mid;// 此时找到key==mid 将mid返回

		}
		return -1;// 没找到key 返回-1报错
	}
}
