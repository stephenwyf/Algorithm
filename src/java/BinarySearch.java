import java.util.*;
public class BinarySearch {//��д���ֲ��ҷ�
	public static void main(String[] args) {
		int[] a={10,9,8,7,6,5,4,3,2,1,0};
		Arrays.sort(a);//����
		System.out.println(binarySearch(1,a));
	}
	public static int binarySearch(int key,int[] a){//���� Ҫ���ҵ���key
		for(int f=0,l=a.length-1;f<=l;){//f---�����һ��   l---�������һ��   f<=l---ѭ������
			int mid=(f+l)/2;// ����һ�������м��� mid
			if(key>a[mid]){// ��key>mid f����mid��һλ
				f=mid+1;
			}else if(key<a[mid]){//��key<mid l����midǰһλ
				l=mid-1;
			}else return mid;// ��ʱ�ҵ�key==mid ��mid����

		}
		return -1;// û�ҵ�key ����-1����
	}
}
