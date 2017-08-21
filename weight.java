import java.util.*;
class weight
{
	public static void main(String args[])
	{
		System.out.println("Enter 2-D array size");
		int m=0,n=0;
		Scanner sc=new Scanner(System.in);
		m=sc.nextInt();
		n=sc.nextInt();
		double arr[][]=new double[m][n];
		double arrTweight[][]=new double[m][n];
		double arrDweight[][]=new double[m][n];
		double arrTsum[]=new double[m];
		double arrDsum[]=new double[n];
		System.out.println("Enter array data:");
		for(int i=0;i<m;i++)
		{
			double sum=0;
			for(int j=0;j<n;j++)
			{
				arr[i][j]=sc.nextInt();
				sum=sum+arr[i][j];
			}
			arrTsum[i]=sum;
		}
		// calculate Dsum here
		for(int i=0;i<n;i++)
		{
			double sum=0;
			for(int j=0;j<m;j++)
			{

				sum=sum+arr[j][i];
			}
			arrDsum[i]=sum;
		}
		
		//calculate Tweight here
		for(int i=0;i<m;i++)
		{
			for(int j=0;j<n;j++)
			arrTweight[i][j]=arr[i][j]/arrTsum[i];
		}
		System.out.println();
		
		//claculate dweight here
		for(int i=0;i<m;i++)
		{
			for(int j=0;j<n;j++)
			arrDweight[i][j]=arr[i][j]/arrDsum[j];
		}
		System.out.println("You have entered:");
		print(arr,m,n);
		System.out.println("T-weight data respectively");
		print(arrTweight,m,n);
		System.out.println("D-weight data respectively");
		print(arrDweight,m,n);
		
	}
	public static void print(double arr[][],int m,int n)
	{
		for(int i=0;i<m;i++)
		{
			for(int j=0;j<n;j++)
			{
				System.out.printf("%.2f",arr[i][j]);
				System.out.print(" ");
			}
			System.out.println();
		}
					System.out.println();
	}

	
}
