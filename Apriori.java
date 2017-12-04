import java.util.*;
import java.io.*;

public class Apriory {
	Scanner sc;
	int rows=0, minSup, minFreq;
    StringTokenizer st;
    String input[], line = "", tmp;
	Map<String,Integer> table;    
    int pass = 2;
    public Apriory(){
		table = new LinkedHashMap<>();
		sc = new Scanner(System.in);
    }
    
    void dataInput(){
    	try{
			FileReader file = new FileReader("Input.csv");
			BufferedReader br = new BufferedReader(file);
		    while((line = br.readLine())!=null)
		    	rows++;
		    input = new String[rows];
		    file = new FileReader("Input.csv");
			br = new BufferedReader(file);
    		System.out.println("\nEnter Minimum Support :");
    		minSup = sc.nextInt();
    		minFreq = Math.round((rows*minSup/100.0f));
		    for(int i=0;i<rows;i++){
		    	input[i]=br.readLine();
		    	st = new StringTokenizer(input[i],",");
		    	line="";
		    	while(st.hasMoreTokens()){
		    		String item = ""+st.nextToken();
		    		line+=item;
		    		if(!table.containsKey(item)){
		    			table.put(item,1);
		    		}else if((line.split(item, -1).length-1)<2){
		    			table.put(item,(table.get(item)+1));
		    		}
		    	}
		    }
        }catch(Exception e){
        	e.printStackTrace();
       	}
    }
    
    void cleanTable(){
    	line = "";
    	for (Map.Entry<String, Integer> entry : table.entrySet())
            if (entry.getValue()<minFreq) 
                line+=entry.getKey()+",";
		if(line.length()>0)
        	line = line.substring(0,line.length()-1); 
        st = new StringTokenizer(line,",");
		while(st.hasMoreTokens())
			table.remove(st.nextToken());
    }
    
    void calcultions(){
    	List<String> list = new ArrayList<String>(table.keySet());
    	Set<String> set = new HashSet<String>();
    	table.clear();
    	for(int i=0;i<list.size();i++){
    		String itemset = list.get(i);
    		for(int j=0;j<itemset.length();j++)
    			set.add(""+itemset.charAt(j));
    	}
    	list = new ArrayList<String>(set);
		String s = "";
    	for(int i=0;i<list.size();i++)
		    s += list.get(i);
		printCombination(s.toCharArray(),s.length());
		pass++;
    }

	void combinationUtil(char arr[], char data[], int start,int end, int index, int r){
        if (index == r){
			line="";
            for (int j=0; j<r; j++)
                line+=data[j];
            table.put(line,getCount(line));
            return;
        }
        for (int i=start; i<=end && end-i+1 >= r-index; i++){
            data[index] = arr[i];
            combinationUtil(arr, data, i+1, end, index+1, r);
        }
    }

	void printCombination(char arr[], int n){
        char data[]=new char[pass];
        combinationUtil(arr, data, 0, n-1, 0, pass);
    }
        
    int getCount(String set){
    	int cnt=0;
    	boolean flag;
    	for(int i=0;i<rows;i++){
    		flag=true;
    		for(int j=0;j<set.length();j++){
    			if(!input[i].contains(""+set.charAt(j))){
    				flag=false;
    				break;
    			}
    		}
    		if(flag)
    			cnt++;
    	}
    	return cnt;
    }
    
    void output(){
       System.out.println(table+"\n");       
    }
       
    public static void main(String args[]){
        Apriory obj = new Apriory();
        System.out.println("\nData is taken from 'Input.csv'.");
        obj.dataInput();
        while(obj.table.size()>0){
        	obj.cleanTable();
        	obj.output();
        	obj.calcultions();      	
        }
    }
}
