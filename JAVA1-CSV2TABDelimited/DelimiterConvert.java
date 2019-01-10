import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.Map;
import java.util.HashMap;
/*

I am not sure if the question is asking for replacing the delimiter with \t
Or just take the school information out and use \t as delimiter for that information
So I did both.
	allOutput.txt is for all the data
	schoolOutput.txt has just the school data (Some delimiters seems like spaces
	but they are tabs.)
*/
public class DelimiterConvert{

	static HashMap<String,List<Student>> students;
	static String FNF_ERR_MSG = "Please make sure you have a data file with the name data.csv and the delimiters are commas(,)";
	static String GEN_ERR_MSG = "General Exception Message";

	static class Student{

		private String _id;
		private String _name;
		private String _school;
		
		public Student(String stuId, String stuName, String stuSchool){
			
			this._id = stuId;
			this._name = stuName;
			this._school = stuSchool;

		}

		public String getSchool(){
			return _school;
		}

		public String toString(){
			return _id + "," +_name;
		}

	}

	static void readData() throws FileNotFoundException{
		Scanner scanner = new Scanner(new File("data.csv"));
		while(scanner.hasNext()){
			String[] dt = scanner.nextLine().split(",",0);
			Student s = new Student(dt[0],dt[1],dt[2]);
			if(!students.containsKey(dt[2]))
				students.put(dt[2],new ArrayList<Student>());
			students.get(dt[2]).add(s);				
		}
		scanner.close();
	}

	static void writeData() throws IOException {

		for(Map.Entry<String, List<Student>> item: students.entrySet()){

			BufferedWriter writer = new BufferedWriter(new FileWriter(item.getKey()));
			//Take all the data of students and put it all together in one list
			String infoList = item.getValue().stream().map(stu -> stu.toString()).collect(Collectors.joining("\t"));
			
			writer.write(infoList);

			writer.close();
		}
	}

	public static void main(String[] args){
		try{
			students = new HashMap<String, List<Student>>();
			readData();
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
