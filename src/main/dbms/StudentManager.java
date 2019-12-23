package main.dbms;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;


public class StudentManager extends JdbcController {
	private List<Student> studentList;
	
	public StudentManager() {
		// TODO Auto-generated constructor stub
		super();
		this.studentList = new Vector<Student>();
		connect(); // conn 객체 생성
	}

//Insert function
	//학생정보를 입력받아 리스트에 저장
	
	public boolean insertStudent(int num, String name, int kor, int eng, int mat) {
		// 같은 번호가 있는지 확인후 반환
		
		try {
			int rows = st.executeUpdate("INSERT INTO APP_STUDENT VALUES "+"("+num
																				+",'"+name
																				+"',"+kor
																				+","+eng
																				+","
																				+mat+")");
			System.out.println(rows + "명을 추가했습니다.");
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
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
		try {
			int row = st.executeUpdate("UPDATE APP_STUDENT SET name='"+name+"',kor="+kor+",eng="+eng+",mat="+mat+"WHERE num="+num);
			System.out.println(row + "개의 행을 수정완료했습니다.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//Read
	//search
	// 번호를 지정하여 학색 인스턴스를 반환하는 메소드
	public Student selectStudent(int num) {
		Student student = new Student();
		try {
			rs = st.executeQuery("SELECT * FROM APP_STUDENT WHERE num = " + num);
			while(rs.next()) {
				student.setNum(rs.getInt(1));
				student.setName(rs.getString(2));
				student.setKor(rs.getInt(3));
				student.setEng(rs.getInt(4));
				student.setMat(rs.getInt(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return student;
	}
	
	//번호의 학생이 리스트에 있는지 확인하는 메소드
	public boolean isStudent(int num) {
		try {
			rs = st.executeQuery("SELECT num FROM APP_STUDENT WHERE num = " + num);
			
			if(!rs.next())return false;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	//모든 리스트를 반환하는 메소드
	public List<Student> getStudentList() {
		return studentList;
	}
	
//문자열로 여러 정보를 반환하는 메소드
	public String print(int num) {
		Student selectS = selectStudent(num);
		ResultSet rs;
		int number = num, kor=0, eng=0, mat=0, tot, rank = 1, avg, cnt = 0;
		int[] avgs = {};
		
		try {
			rs = st.executeQuery("SELECT * FROM APP_STUDENT WHERE num = " + num);
			while(rs.next()) {
				kor = rs.getInt(3);
				eng = rs.getInt(4);
				mat = rs.getInt(5);
			}
			rs = st.executeQuery("SELECT kor, eng, mat FROM APP_STUDENT WHERE num <> " + num);
			while(rs.next()) { cnt++; } // 카운트 생성
			rs.beforeFirst();
			avgs = new int[cnt];
			int i = 0;
			while(rs.next()) {
				avgs[i] = (rs.getInt(1) + rs.getInt(2) + rs.getInt(3))/3;
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		tot = kor+eng+mat;
		avg = tot/3;
		for(int tmp : avgs) {
			if(tmp > avg) rank++;
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
		String name = "";
		try {
			rs = st.executeQuery("SELECT name FROM APP_STUDENT WHERE num = " + num);
			rs.next();
			name = rs.getString(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return name; 
	}
	
//Delete
	//학생번호를 선택해서 해당학생을 지우는 메소드
	public boolean deleteStudent(int num) {
		Student student = selectStudent(num);
		if(student == null) return false;
		try {
			int row = st.executeUpdate("DELETE FROM APP_STUDENT WHERE num="+num);
			System.out.println(row + "행을 삭제했습니다.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
}