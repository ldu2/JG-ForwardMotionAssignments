import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

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

		public String toString(){
			return _id + "\t" +_name+"\t"+_school; 
		}

	}

	static void readData() throws FileNotFoundException{
		Scanner scanner = new Scanner(new File("data.csv"));
		scanner.useDelimiter(",");
		while(scanner.hasNext()){
		    System.out.print(scanner.next());
		}
		scanner.close();
	}

	public static void main(String[] args) throws FileNotFoundException{
		readData();
	}


}
