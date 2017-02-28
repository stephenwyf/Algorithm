
public class QuickSort {
	public static void main(String[] args) {
		int[] a={10,8,6,9,12,3,4,1};
		Quicksort(0,a.length-1,a);
		for(int i:a){
			System.out.print(i+" ");
		}
	}
	public static void Quicksort(int start,int end,int[] a){
		int f=start;//f Ϊ0
		int l=end;//l  Ϊa.length
		if(f>=l){// ����void  ����
			return;
		}
		boolean flag=true; //��������ʱ�����ж�
		while(f!=l){//��f l�غ�ʱһ�ο��Ž���
			if(a[f]>a[l]){//����λ��
				int temp=0;
				temp=a[f];
				a[f]=a[l];
				a[l]=temp;
				flag=!flag;// �ı� �ƶ�����
			}
			if(flag){// ��ʱ  �ҡ�����
				l--;
			}else f++;//  �󡷡��� 	
		}
		f--;// ��ʱ f=l  ��f ��Ϊǰһλ
		l++;//l ��Ϊ��һλ
		Quicksort(start,f,a);// ��ʣ�µ�����ֳ����� �ֱ���п���    0--����ǰһλ
		Quicksort(l,end,a);// ���Ӻ�һλ--a.length
	}

}
