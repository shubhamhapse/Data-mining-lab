import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

class InfoGain {
    static int dataAttributes=0;
    static Set dataAttrubuteSet[];
    static int lineCount=0;
    static String data[][];
    static String filePath="/home/it/Music/dm/infodata.csv";
    public final String targetValue="no";

    public static void main(String args[]) {
        InfoGain infoGain = new InfoGain();
        infoGain.readAttributeLength();
        infoGain.createSetArrayAccordingtoDataLength();
        infoGain.addAttributestoSet();
        //infoGain.displayAttributeSet();
        infoGain.addDataToStringArray();
        infoGain.displayDataSet();
        double d=infoGain.findEntropyForTargetedColumn(dataAttributes-1);
        for(int i=0;i<dataAttributes-1;i++) {
            System.out.printf("%.3f  ",infoGain.findEntropyForColumn(1));
        }
       System.out.printf("%.3f\n",d);
        for(int i=0;i<dataAttributes-1;i++) {
         System.out.printf("%.3f  ",d-infoGain.findEntropyForColumn(i));

        }
    }

    public void readAttributeLength() {
       String line;
        BufferedReader bufRdr = null;
        try {
            bufRdr = new BufferedReader(new FileReader(filePath));
            if ((line = bufRdr.readLine()) != null) {
                String arr[]=line.split(",");
                dataAttributes=arr.length;

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
            for(int i=0;i<lineCount;i++)
            {
                if(item.equals(data[column][i]))
                    itemCount++;
            }
           // System.out.print(itemCount);
            if(itemCount!=0)
            d=d+(itemCount/lineCount)*Math.log(itemCount/lineCount)/Math.log(2);
        }
      //  System.out.println(-d);
     return -d;
    }
    public double findEntropyForColumn(int column)
    {
        Iterator iterator=dataAttrubuteSet[column].iterator();
        double d=0;
        while (iterator.hasNext())
        {
            double itemCount=0;
            double itemYes=0;
            String item=iterator.next().toString();
          //  System.out.println(item);
            for(int i=0;i<lineCount;i++)
            {
                if(item.equals(data[column][i]))
                {
                    itemCount++;
                    if(targetValue.equals(data[dataAttributes-1][i]))
                        itemYes++;
                }
            }
           // System.out.println(item+"  "+itemCount+"  "+itemYes);
            if(itemCount!=itemYes && itemCount!=0 && itemYes!=0) {
                d = d + (itemCount / lineCount) * (itemYes / itemCount) * Math.log(itemYes / itemCount) / Math.log(2)
                        + (itemCount / lineCount) * ((itemCount - itemYes) / itemCount) * Math.log((itemCount - itemYes) / itemCount) / Math.log(2);
            }
            else if(itemCount!=0 && itemYes!=0)
            {   d=d+(itemCount/lineCount)*(itemYes/itemCount)*Math.log(itemYes/itemCount)/Math.log(2);

            }
        }
       // System.out.println(-d);
        return -d;
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



