import java.util.*;
class NumberSummary
{
	static int flag=0;
	public static void main(String args[])
	{
		int count=0;
		double min,max,q1,q2,q3;
		Scanner sc=new Scanner(System.in);
		count=sc.nextInt();
		if(count%2==0)
			flag=1;
		int arr[]=new int[count];
		for(int i=0;i<count;i++)
			arr[i]=sc.nextInt();
		Arrays.sort(arr);
		min=arr[0];
		max=arr[count-1];
		q2=findMedian(arr,0,count-1);
		if(count%2==0)
		{
			q1=findMedian(arr,0,count/2)+findMedian(arr,0,count/2-1);
			q1=q1/2;

			q3=findMedian(arr,count/2-1,count-1)+findMedian(arr,count/2,count-1);
			q3=q3/2;
		}
		else
		{
			q1=findMedian(arr,0,count/2);
			q3=findMedian(arr,count/2,count-1);
		}
		System.out.println(min+"  "+q1+"  "+q2+"  "+q3+"  "+max);
	}
	public static double findMedian(int arr[],int start,int last)
	{
		int count=last-start;
		double q2;		
		if(count%2!=0)
		{
			System.out.println(arr[count/2]+"  "+arr[count/2+1]);
			q2=arr[start+count/2]+arr[start+count/2+1];
			q2=q2/2;
		}
		else
		{
			q2=arr[start+count/2];		
		}
		return q2;
	}
}
//1 2 3 4 5
