package main.nondbms;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;

public class StudentManager{
	private List<Student> studentList;
	
	public StudentManager() {
		// TODO Auto-generated constructor stub
		super();
		this.studentList = new Vector<Student>();
	}

//Insert function
	//학생정보를 입력받아 리스트에 저장
	
	public boolean insertStudent(int num, String name, int kor, int eng, int mat) {
		// 같은 번호가 있는지 확인후 반환
		for(Student s  : studentList) {
			if(s.getNum() == num)	return false;	
		}
		studentList.add(new Student(num,name,kor,eng,mat));
		return true;
	}
	
//Update function
	//점수를 변경하는 메서드
	
	//번호와 과목점수를 받아서 리스트를 바꾸는 메소드
	/*
	public boolean updateKor(int num, int kor) {
		Student student = selectStudent(num); 
		if(student == null) return false;
		student.setKor(kor); 
		return true;
	}
	public boolean updateEng(int num, int eng) {
		Student student = selectStudent(num); 
		if(student == null) return false;
		student.setKor(eng); 
		return true;
	}
	public boolean updateMat(int num, int mat) {
		Student student = selectStudent(num); 
		if(student == null) return false;
		student.setKor(mat); 
		return true;
	}
	*/
	public void updateAll(int num, String name, int kor, int eng, int mat) {
		Student student = selectStudent(num); 
		student.setName(name);
		student.setKor(kor);
		student.setEng(eng);
		student.setMat(mat);
	}
//Read
	//search
	// 번호를 지정하여 학색 인스턴스를 반환하는 메소드
	public Student selectStudent(int num) {
		for(Student s  : studentList) {
			if(s.getNum() == num) return s;
		}
		return null;
	}
	
	//번호의 학생이 리스트에 있는지 확인하는 메소드
	public boolean isStudent(int num) {
		for(Student s  : studentList) {
			if(s.getNum() == num) return true;
		}
		return false;
	}
	//모든 리스트를 반환하는 메소드
	public List<Student> getStudentList() {
		return studentList;
	}
	
//문자열로 여러 정보를 반환하는 메소드
	public String print(int num) {
		Student selectS = selectStudent(num);
		int number = selectS.getNum();
		int kor = selectS.getKor();
		int eng = selectS.getEng();
		int mat = selectS.getMat();
		int tot = kor+eng+mat;
		int rank = 1;
		
		for(Student student : studentList) {
			if(student.getKor() + student.getEng() + student.getMat() > tot) {
				rank++;
			}
		}
		
		
		
		return "number : " + number + "\n"
				+ "kor : " + kor + "\n"
				+ "eng : " + eng + "\n"
				+ "mat : " + mat + "\n"
				+ "tot : " + tot + "\n"
				+ "avg : " + tot/3 + "\n"
				+ "rank : " + rank;
	}
	public String namePrint(int num) {
		return selectStudent(num).getName();
	}
	
//Delete
	//학생번호를 선택해서 해당학생을 지우는 메소드
	public boolean deleteStudent(int num) {
		Student student = selectStudent(num);
		if(student == null) return false;
		studentList.remove(student);
		return true;
	}
	//모든학생정보를 지우는 메소드
	public boolean deleteAll() {
		if(studentList.size() == 0) return false;
		studentList.clear();
		return true;
	}
	
//학생정보를 콘솔에 출력하는 메소드(테스트용)
	public void printList(String txt) {
		System.out.println(txt);
		for(Student student : studentList) {
			System.out.println(student);
		}
	}
//TEST
	public static void main(String[] args) {
		StudentManager s = new StudentManager();
		s.insertStudent(123, "aaa", 10, 10, 10);
		s.insertStudent(234, "aaa", 20, 20, 20);
		String[][] content = new String [s.studentList.size()][5];
		
		for(int row = 0; row < content.length; row++) {
			for(int col = 0; col < content[row].length; col++) {
				switch (col) {
				case 0: content[row][col] = s.studentList.get(row).getNum()+""; break;
				case 1: content[row][col] = s.studentList.get(row).getName(); break;
				case 2: content[row][col] = s.studentList.get(row).getKor()+""; break;
				case 3: content[row][col] = s.studentList.get(row).getEng()+""; break;
				case 4: content[row][col] = s.studentList.get(row).getMat()+""; break;
				}
			}
		}
		
		for(String[] strs : content) {
			for(String str : strs) {
				System.out.print(str + "\t");
			}
			System.out.println();
		}
	}
}