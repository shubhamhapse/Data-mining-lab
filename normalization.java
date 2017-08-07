import java.util.*;
class normalization
{
	public static void main(String args[])
	{
		int n,arr[],low,high;
		Scanner sc=new Scanner(System.in);
		System.out.print("Enter data length");
		n=sc.nextInt();
		arr=new int[n];
		for(int i=0;i<n;i++)
		{
			arr[i]=sc.nextInt();
		}
		Arrays.sort(arr);
		System.out.println("Enter new data boundry");
		low=sc.nextInt();
		high=sc.nextInt();
		minMax(arr,low,high);
		zScore(arr);
	}
	
	
	public static void minMax(int arr[],int low,int high)
	{
		int preMin=arr[0];
		int preMax=arr[0];
		for(int i=1;i<arr.length;i++)
		{
			if(arr[i]>preMax)
				preMax=arr[i];
			if(arr[i]<preMin)
				preMin=arr[i];
		}
		System.out.println("New array will be:");
		for(int i=0;i<arr.length;i++)
		{
			int p=(arr[i]-preMin)*(high-low)/(preMax-preMin)+low;
			System.out.print(p+" ");
		}
		System.out.println();
	}
	
	public static void zScore(int arr[])
	{
		int sum=0;
		double variance=0,sd;
		for(int i=0;i<arr.length;i++)	
			sum=sum+arr[i];
		double mean=sum/arr.length;
		for(int i=0;i<arr.length;i++)
		{
			variance=variance+(arr[i]-mean)*(arr[i]-mean);
		}
		sd=Math.sqrt(variance/(arr.length-1));
		System.out.print("New z-score values will be ");
		for(int i=0;i<arr.length;i++)
		{
			System.out.println((arr[i]-mean)/sd+"  ");
				
		}
	}
	
}
