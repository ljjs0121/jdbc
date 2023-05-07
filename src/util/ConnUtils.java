package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
// JVM 드라이버 등록, Connection 구현객체 메소드 생성
public class ConnUtils {
	
	private static final String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String username = "hr";
	private static final String password = "zxcv1234";
	
	static {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, username, password);
	}
}