import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

class Classification1 {
    static int dataAttributes=0;
    static Set dataAttrubuteSet[];
    static int lineCount=0;
    static String data[][];
    static double probabilityPerAttributes[];
    static String filePath="/home/it/Pictures/infodata.csv";
   // public static String targetValue="no";

    public static void main(String args[]) {
        Classification1 classification = new Classification1();
        classification.readAttributeLength();
        classification.createSetArrayAccordingtoDataLength();
        classification.addAttributestoSet();
        classification.addDataToStringArray();
        classification.displayDataSet();
        double targetProbability=classification.findEntropyForTargetedColumn(dataAttributes-1);
        double targetProbability1=classification.findEntropyForTargetedColumn1(dataAttributes-1);

        //System.out.print("probability "+targetProbability);
        double finalProbability=targetProbability;
        double finalProbability1=targetProbability1;
        //System.out.println(targetProbability1);
        Scanner scanner=new Scanner(System.in);
        for(int i=0;i<dataAttributes-1;i++)
        {
            System.out.println("Enter Attributes:");
            String s=scanner.nextLine();
            finalProbability=finalProbability*classification.findEntropyForColumn(i,s);
            finalProbability1=finalProbability1*classification.findEntropyForColumn1(i,s);

        }
        System.out.println("Probability of targeted output no is :"+finalProbability);
        System.out.println("Probability of targeted output yes is :"+finalProbability1);

    }

    public void readAttributeLength() {
        String line;
        BufferedReader bufRdr = null;
        try {
            bufRdr = new BufferedReader(new FileReader(filePath));
            if ((line = bufRdr.readLine()) != null) {
                String arr[]=line.split(",");
                dataAttributes=arr.length;
                probabilityPerAttributes=new double[dataAttributes-1];

            }
        } catch (Exception e) {
            System.out.println("Error while reading file");
        }

    }
    public void createSetArrayAccordingtoDataLength()
    {
        dataAttrubuteSet=new TreeSet[dataAttributes];
        for(int i=0;i<dataAttributes;i++) {
            dataAttrubuteSet[i] = new TreeSet();
        }
    }
    public void addAttributestoSet()
    {
        String line;
        BufferedReader bufRdr = null;
        try {
            bufRdr = new BufferedReader(new FileReader(filePath));
            while ((line = bufRdr.readLine()) != null) {
                String arr[]=line.split(",");
                for(int i=0;i<dataAttributes;i++)
                {
                    //System.out.println(arr[i]);
                    dataAttrubuteSet[i].add(arr[i]);
                }
                lineCount++;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
    public void addDataToStringArray()
    {
        data=new String[dataAttributes][];
        for(int i=0;i<dataAttributes;i++)
            data[i]=new String[lineCount];
        String line;
        int j=0;
        BufferedReader bufRdr = null;
        try {
            bufRdr = new BufferedReader(new FileReader(filePath));
            while ((line = bufRdr.readLine()) != null) {
                String arr[]=line.split(",");
                for(int i=0;i<dataAttributes;i++)
                {
                    data[i][j]=arr[i];
                    // System.out.println(data[i][j]);
                }
                j++;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }
    public double findEntropyForTargetedColumn(int column)
    {
        double d=0;
        Iterator iterator=dataAttrubuteSet[column].iterator();
        while (iterator.hasNext())
        {
            double itemCount=0;
            String item=iterator.next().toString();
            for(int i=0;i<lineCount && item.equals("no");i++)
            {
                if(item.equals(data[column][i]))
                    itemCount++;
            }
            if(itemCount!=0)
                d=(itemCount/lineCount);
        }
        return d;
    }
    public double findEntropyForTargetedColumn1(int column)
    {
        double d=0;
        Iterator iterator=dataAttrubuteSet[column].iterator();
        while (iterator.hasNext())
        {
            double itemCount=0;
            String item=iterator.next().toString();
            for(int i=0;i<lineCount && item.equals("yes");i++)
            {
                if(item.equals(data[column][i]))
                    itemCount++;
            }
            if(itemCount!=0)
                d=(itemCount/lineCount);
        }
        return d;
    }
    public double findEntropyForColumn(int column,String s)
    {
        Iterator iterator=dataAttrubuteSet[column].iterator();
        double d=0;
        while (iterator.hasNext())
        {
            double itemCount=0;
            double itemYes=0;
            String item=iterator.next().toString();
            for(int i=0;i<lineCount && item.equals(s);i++)
            {
                if(item.equals(data[column][i]))
                {
                    itemCount++;
                    if("no".equals(data[dataAttributes-1][i]))
                        itemYes++;
                }
            }
            //  System.out.println(item+"  "+itemCount+"  "+itemYes);

            if(itemCount!=0 && itemYes!=0)
            {   d=(itemYes/itemCount);

            }
        }
        // System.out.println(d);
        return d;
    }
    public double findEntropyForColumn1(int column,String s)
    {
        Iterator iterator=dataAttrubuteSet[column].iterator();
        double d=0;
        while (iterator.hasNext())
        {
            double itemCount=0;
            double itemYes=0;
            String item=iterator.next().toString();
            for(int i=0;i<lineCount && item.equals(s);i++)
            {
                if(item.equals(data[column][i]))
                {
                    itemCount++;
                    if("yes".equals(data[dataAttributes-1][i]))
                        itemYes++;
                }
            }
            //  System.out.println(item+"  "+itemCount+"  "+itemYes);

            if(itemCount!=0 && itemYes!=0)
            {   d=(itemYes/itemCount);

            }
        }
        // System.out.println(d);
        return d;
    }
    public void displayDataSet()
    {

        System.out.println("Your data set:");
        for(int j=0;j<lineCount;j++)
        {
            for(int i=0;i<dataAttributes;i++) {
                System.out.print(data[i][j]+"  ");
            }
            System.out.println();

        }
    }
    public void displayAttributeSet()
    {
        System.out.println("attributes set");
        for(int i=0;i<dataAttributes;i++)
        {
            Iterator iterator=dataAttrubuteSet[i].iterator();
            while (iterator.hasNext()){
                System.out.print(iterator.next().toString()+"   ");
            }
            System.out.println();
        }
    }
}


