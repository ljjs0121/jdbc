package sample02;
// 영속화 계층 DataBase

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import util.ConnUtils;

public class ScoreDao {

	/**
	 * 성적정보를 전달받아서 SAMPLE_SCORES 테이블에 추가한다. (INSERT)
	 * @param score 성적정보
	 */
	public void insertScore(Score score) throws SQLException {
		String sql = "insert into sample_scores "
				+ "(student_no, student_name, kor_score, eng_score, math_score) "
				+ "values "
				+ "(sample_student_seq.nextval, ?, ?, ?, ?)";
		Connection con = ConnUtils.getConnection();
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, score.getStudentName());
		pstmt.setInt(2, score.getKor());
		pstmt.setInt(3, score.getEng());
		pstmt.setInt(4, score.getMath());
		
		pstmt.executeUpdate();
		
		pstmt.close();
		con.close();
	}
	
	/**
	 * SAMPLE_SCORES 테이블의 모든 성적정보를 반환한다. (SELECT)
	 * @return 모든 성적정보, 성적정보가 존재하지 않으면 빈 {@code List<Score>} 객체가 반환된다.
	 */
	public List<Score> getAllScores() throws SQLException {
		String sql = "select student_no, student_name, kor_score, eng_score, math_score, create_date "
				+ "from sample_scores "
				+ "order by student_no asc ";
		
		List<Score> scoreList = new ArrayList<>();
		
		Connection con = ConnUtils.getConnection();
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		// 커서가 위치한(rs.next()) 행의 값 조회
		while (rs.next()) {
			int no = rs.getInt("student_no");
			String name = rs.getString("student_name");
			int kor = rs.getInt("kor_score");
			int eng = rs.getInt("eng_score");
			int math = rs.getInt("math_score");
			Date createDate = rs.getDate("create_date");
			
			// 조회된 값 저장
			Score score = new Score();
			score.setStudentNo(no);
			score.setStudentName(name);
			score.setKor(kor);
			score.setEng(eng);
			score.setMath(math);
			score.setCreateDate(createDate);
			
			// 조회된 값 넣기
			scoreList.add(score);
		}
		
		rs.close();
		pstmt.close();
		con.close();
		
		return scoreList;
	}

	/**
	 * 전달받은 학생의 성적정보를 SAMPLE_SCORES 테이블에서 조회해서 반환한다.
	 * @param studentNo 학생번호
	 * @return 성적정보, 성적정보가 존재하지 않으면 null을 반환한다.
	 */
	public Score getScoreByStudentNo(int studentNo) throws SQLException {
		String sql = "select student_no, student_name, kor_score, eng_score, math_score, create_date "
				+ "from sample_scores "
				+ "where student_no = ? ";
		
		Score score = null;
		
		Connection con = ConnUtils.getConnection();
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, studentNo);
		ResultSet rs = pstmt.executeQuery();

		// 값을 하나만 가져오기 때문에 if로도 가능하다
		while (rs.next()) {
			int no = rs.getInt("student_no");
			String name = rs.getString("student_name");
			int kor = rs.getInt("kor_score");
			int eng = rs.getInt("eng_score");
			int math = rs.getInt("math_score");
			Date createDate = rs.getDate("create_date");
			
			score = new Score();
			score.setStudentNo(no);
			score.setStudentName(name);
			score.setKor(kor);
			score.setEng(eng);
			score.setMath(math);
			score.setCreateDate(createDate);
		}
		
		rs.close();
		pstmt.close();
		con.close();
		
		return score;
	}
	
	/**
	 * 전달받은 학생의 성적정보를 SAMPLE_SCORES 테이블에서 삭제한다
	 * @param studentNo 학생번호
	 * @throws SQLException 
	 */
	public void deleteScore(int studentNo) throws SQLException {
		String sql = "deledte from sample_score where student_no ";
		
		Connection con = ConnUtils.getConnection();
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, studentNo);
		pstmt.executeUpdate();
		
		pstmt.close();
		con.close();
		
	}
	
	/**
	 * 수정된 정보가 포함된 성적정보를 전달받아서 SAMPLE_SCORES 테이블에 반영시킨다.
	 * @param score
	 * @throws SQLException 
	 */
	public void updateScore(Score score) throws SQLException {
		String sql = "update sample_score "
				+ "set "
				+ "kor_score = ?, "
				+ "eng_score = ?, "
				+ "math_score = ? "
				+ "where student_no = ? ";
		
		Connection con = ConnUtils.getConnection();
		PreparedStatement pstmt =con.prepareStatement(sql);
		
		pstmt.close();
		con.close();
		
	}
	
}







