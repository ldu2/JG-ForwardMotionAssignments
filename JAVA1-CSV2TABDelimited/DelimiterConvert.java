import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.stream.Stream;
import java.util.stream.Collectors;

public class DelimiterConvert{

	static List<Student> students;

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
			return _id + "\t" +_name+"\t"+_school+"\n";
		}

	}

	static void readData() throws FileNotFoundException{
		Scanner scanner = new Scanner(new File("data.csv"));
		while(scanner.hasNext()){
			String[] dt = scanner.nextLine().split(",",0);
			Student s = new Student(dt[0],dt[1],dt[2]);
			students.add(s);
		}
		scanner.close();
	}

	static void writeAllData() throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter("allOutput.txt"));
		
		List<String> infoList = students.stream().map(stu -> stu.toString()).collect(Collectors.toList());
		
		writer.write(String.join("",infoList));

		writer.close();
	}

	static void writeSchoolData() throws IOException {
		
		BufferedWriter writer = new BufferedWriter(new FileWriter("schoolOutput.txt"));

		List<String> schoolList = students.stream().map(stu -> stu.getSchool()).collect(Collectors.toList());

		writer.write(String.join("\t",schoolList));
		
		writer.close();
	}

	public static void main(String[] args){
		try{
			students = new ArrayList<Student>();
			readData();
			writeAllData();
			writeSchoolData();

		}catch(FileNotFoundException e){
			System.out.println("Please make sure you have a data file with the name data.csv and the delimiters are commas(,)");
		}
		catch(IOException e){
			System.out.println("Please make sure you have a data file with the name data.csv and the delimiters are commas(,)");
		}
	
	}


}
