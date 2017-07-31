import java.util.Arrays;
import java.util.Scanner;

public class bin {
    public static void main(String args[])
    {
        System.out.println("Enter length of array");
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int arr[]=new int[n];
        for(int i=0;i<n;i++)
        {
            arr[i]=sc.nextInt();
        }
        System.out.println("Enter size of bin");
        int binSize=sc.nextInt();
        Arrays.sort(arr);

        System.out.println("Binning using mean");
        binningMean(arr,n,binSize);

        System.out.println("Binning using median");
        binningMedian(arr,n,binSize);

        System.out.println("Binning using boundries");
        binningBoundries(arr,n,binSize);

    }

    public static void binningMean(int ar[],int n,int binSize)
    {
        int sum=0,count=0;
        for(int i=0;i<n;i+=binSize)
        {
            for(int k=0;k<binSize && (i+k)<n;k++) {
                sum = sum + ar[i + k];
                count++;
            }
            System.out.print("bin "+(i/binSize+1)+" calculation using mean contains =>");
            for (int j=0;j<count;j++)
                System.out.print(sum/count+" ");
            System.out.println();
            sum=0;
            count=0;
        }
        System.out.println();

    }


    public static void binningMedian(int ar[],int n,int binSize)
    {
        int sum=0,count=0,median=0;
        for(int i=0;i<n;i+=binSize)
        {
            if(i+binSize<n)
                count=binSize;
            else
                count=n-i;
            if(count%2==0)
            {
                median=(ar[i+count/2]+ar[i+count/2-1])/2;
            }
            else if(i+count/2<n)
                median=ar[i+count/2];
            System.out.print("bin "+(i/binSize+1)+"calculation using median contains =>");
            for (int j=0;j<count;j++)
                System.out.print(median+" ");
            System.out.println();
            sum=0;
            count=0;
        }
        System.out.println();

    }

    public static void binningBoundries(int ar[],int n,int binSize)
    {
        int count=0,min=0,max=0;
        for(int i=0;i<n;i+=binSize)
        {
            for(int k=0;k<binSize && (i+k)<n;k++) {
                count++;
            }
            min=ar[i];
            max=ar[i+count-1];
            System.out.print("bin "+(i/binSize+1)+" calculation using boundries contains =>");
            System.out.print(ar[i]+" ");
            for (int j=1;j<count-1;j++){
                if((ar[i+j]-min)<(max-ar[i+j])){
                    System.out.print(min+" ");
                }
                else
                    System.out.print(max+" ");
            }
            if(count!=1)
                System.out.print(max+" ");

            System.out.println();
            count=0;
        }
        System.out.println();

    }

}
