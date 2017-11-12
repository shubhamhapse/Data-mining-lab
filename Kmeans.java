import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class Kmeans {
    static int lineCount = 0;
    static int clusterCount = 3;
    static int dataAttributes = 0;
    static double data[][];
    static boolean flag = true;

    static String filePath = "/home/it/Music/dm/InfoGain/dm/data.csv";

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter cluster Count:");
        clusterCount = sc.nextInt();
        Kmeans kmeans = new Kmeans();
        kmeans.readAttributeLength();
        kmeans.getDataLength();
        kmeans.createDataStructure();
        kmeans.addData();
        kmeans.initCluster();
        kmeans.formCluster();
        kmeans.displayData();
        kmeans.displayClusters();
        // kmeans.euclideanDist(0,clusterCount-1);


    }

    public void readAttributeLength() {
        String line;
        BufferedReader bufRdr = null;
        try {
            bufRdr = new BufferedReader(new FileReader(filePath));
            if ((line = bufRdr.readLine()) != null) {
                String arr[] = line.split(",");
                dataAttributes = arr.length;

            }
        } catch (Exception e) {
            System.out.println("Error while reading file");
        }

    }

    public void createDataStructure() {
        data = new double[dataAttributes + 2][];
        for (int i = 0; i < dataAttributes + 2; i++) {
            data[i] = new double[lineCount + clusterCount];
        }
    }

    public void getDataLength() {
        String line;
        BufferedReader bufRdr = null;
        try {
            bufRdr = new BufferedReader(new FileReader(filePath));
            while ((line = bufRdr.readLine()) != null) {
                lineCount++;
            }
        } catch (Exception e) {
            System.out.println("Error while reading file");
        }
    }

    public void addData() {
        String line;
        int j = clusterCount;
        BufferedReader bufRdr = null;
        try {
            bufRdr = new BufferedReader(new FileReader(filePath));
            while ((line = bufRdr.readLine()) != null) {
                String arr[] = line.split(",");
                for (int i = 0; i < dataAttributes; i++) {
                    data[i][j] = Integer.parseInt(arr[i]);
                    data[dataAttributes + 1][j] = Integer.MAX_VALUE;
                    // System.out.print(data[i][j]+" ");
                }
                j++;
            }
        } catch (Exception e) {
            System.out.println("Error while reading file");
        }
    }

    public void displayData() {
        for (int j = 0; j < lineCount + clusterCount; j++) {

            for (int i = 0; i < dataAttributes + 2; i++) {
                System.out.print(data[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void initCluster() {
        for (int i = 0; i < clusterCount; i++) {
            for (int j = 0; j < dataAttributes; j++) {
                data[j][i] = data[j][i + clusterCount];
            }
        }
    }

    public void formCluster() {
        while (flag) {
            flag = false;
            for (int i = clusterCount; i < lineCount + clusterCount; i++) {
                double dist = data[dataAttributes + 1][i];
                for (int j = 0; j < clusterCount; j++) {
                    double temp = euclideanDist(j, i);
                    if (temp < dist) {
                        dist = temp;
                        data[2][i] = j;
                        data[dataAttributes + 1][i] = dist;
                        //  System.out.println("found new cluster" + dist);
                        // updateAllClusters();
                        //update each cluster
                    }

                }
            }

            updateAllClusters();
        }

    }

    public void updateAllDistances() {
        for (int i = clusterCount; i < clusterCount + lineCount; i++) {
            data[dataAttributes + 1][i] = euclideanDist((int) data[dataAttributes][i], i);
        }
    }

    public void updateAllClusters() {
        for (double i = 0; i < clusterCount; i++) {
            double x = 0, y = 0;
            int count = 0;
            for (int j = clusterCount; j < lineCount + clusterCount; j++) {
                if ((double) data[dataAttributes][j] == i) {
                    x = x + data[0][(int) j];
                    y = y + data[1][(int) j];
                    count++;
                }
            }
            System.out.println("update cluster:" + x + "  " + y + "  " + count);
            if (data[0][(int) i] != x / count) {
                data[0][(int) i] = x / count;
                flag = true;
            }
            if (data[1][(int) i] != y / count) {
                flag = true;
                data[1][(int) i] = y / count;
            }
            if(flag)
                updateAllDistances();
            //   displayData();
        }
    }

    public void displayClusters() {
        for (int i = 0; i < clusterCount; i++) {
            System.out.println("Cluster " + i + ":-");
            for (int j = clusterCount; j < lineCount + clusterCount; j++) {
                if (data[dataAttributes][j] == (double) i)
                    System.out.print(j - clusterCount + 1 + "   ");
            }
            System.out.println();
        }
    }

    public double euclideanDist(int clusterId, int point) {
        //  System.out.print(Math.sqrt(((data[0][point]-data[0][clusterId])*(data[0][point]-data[0][clusterId]))
        //+((data[1][point]-data[1][clusterId])*(data[1][point]-data[1][clusterId]))));
        return Math.sqrt(((data[0][point] - data[0][clusterId]) * (data[0][point] - data[0][clusterId]))
                + ((data[1][point] - data[1][clusterId]) * (data[1][point] - data[1][clusterId])));
    }
}
