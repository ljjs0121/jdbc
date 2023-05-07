package sample01;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import util.ConnUtils;

// SELECT
public class App2 {

	public static void main(String[] args) throws Exception {
		
		// 쿼리 정의
		String sql = "select student_no, student_name, kor_score, eng_score, math_score "
				+ "from sample_scores "
				+ "order by student_no asc ";
		// Connection 획득
		Connection con = ConnUtils.getConnection();
		// 
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		// DB 데이터 가져오기 
		while (rs.next()) {
			// ResultSet은 가져오는것이 get 메소드이다
			// PrepareStatement는 가져오는것이 set 메소드이다
			int no = rs.getInt("student_no");
			String name = rs.getString("student_name");
			int kor = rs.getInt("kor_score");
			int eng = rs.getInt("eng_score");
			int math = rs.getInt("math_score");
			
			System.out.println("학번: " + no);
			System.out.println("이름: " + name);
			System.out.println("국어: " + kor);
			System.out.println("영어: " + eng);
			System.out.println("수학: " + math);
			System.out.println();
			
		}
		
		// 자원 반납
		rs.close();
		pstmt.close();
		con.close();
	}
}
