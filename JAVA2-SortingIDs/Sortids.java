import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.stream.Collectors;

/*
	My solution to this might be more complicated than it should be. Not sure about that.
	Overall, I am trying to do a selection sort. Not really going for performance here.
	Since the sort is in-place. I read the data in the Lists and convert to Arrays to sort.
	Then merge the arrays together after sorting and write it to the output file.
	
	I believe we should seperate the numbers and text ids according to the question.
*/

public class Sortids{
	//the ids that are strings
	static List<String> dataStr;
	//the ids that are integers
	static List<Integer> dataNum;
	//the arrays for the string ids and integer ids
	static String[] dataStrArr;
	static int[] dataNumArr;
	static String FNF_ERR_MSG = "Please make sure you have a data file with the name data.csv and the delimiters are commas(,)";
	static String GEN_ERR_MSG = "General Exception Message";
	//if return value larger than 0, then str1>str2
	//if less than 0, str1<str2
	//if 0, they are same string
	static int compareStr(String str1, String str2){
		
		int strLen1 = str1.length();
		int strLen2 = str2.length();
		for(int i=0;i<strLen1 && i<strLen2;i++){
			if(str1.charAt(i)!=str2.charAt(i))
				return str1.charAt(i) - str2.charAt(i);
		}
		return strLen1 - strLen2;
	}

	static void readData() throws FileNotFoundException{
		Scanner scanner = new Scanner(new File("data.txt"));
		while(scanner.hasNext()){
			String id = scanner.next();
			if(id.matches("[0-9]+"))
				dataNum.add(Integer.parseInt(id));
			else
				dataStr.add(id);
			
		}
		dataStrArr = dataStr.toArray(new String[dataStr.size()]);
		//convert the int to Integer
		dataNumArr = dataNum.stream().mapToInt(i -> i).toArray();

		scanner.close();
	}

	static void writeData() throws IOException {

		BufferedWriter writer = new BufferedWriter(new FileWriter("output.csv"));
		
		writer.write(String.join(",",dataStrArr));

		writer.close();
	}

	//have to sort them seperately

	static void sortData(){
		int strSize = dataStrArr.length;
		int numSize = dataNumArr.length;
		for(int i=0;i<strSize;i++){
			for(int j=i;j<strSize;j++){
				if(compareStr(dataStrArr[i],dataStrArr[j])>0){
					String temp = dataStrArr[i];
					dataStrArr[i] = dataStrArr[j];
					dataStrArr[j] = temp;
				}
			}		
		}
		for(int i=0;i<numSize;i++){
			for(int j=i;j<numSize;j++){
				if(dataNumArr[i]>dataNumArr[j]){
					int temp = dataNumArr[i];
					dataNumArr[i] = dataNumArr[j];
					dataNumArr[j] = temp;
				}
			}		
		}
		//Convert the int array to String array
		List<String> dataNumStrArray = Arrays.stream(dataNumArr)
						.mapToObj(String::valueOf)
						.collect(Collectors.toList());
	
		dataStr = new ArrayList<String>(Arrays.asList(dataStrArr));
		dataStr.addAll(dataNumStrArray);

		dataStrArr=dataStr.toArray(new String[dataStr.size()]);
	}

	public static void main(String[] args){
		try{
			dataStr = new ArrayList<String>();
			dataNum = new ArrayList<Integer>();
			readData();
			sortData();
			writeData();
		}catch(FileNotFoundException e){
			System.out.println(FNF_ERR_MSG);
			e.printStackTrace();
		}catch(Exception e){
			System.out.println(GEN_ERR_MSG);
			e.printStackTrace();
		}
		
	}

}
