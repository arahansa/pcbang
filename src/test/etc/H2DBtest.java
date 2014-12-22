package test.etc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class H2DBtest {

	private static PreparedStatement pstmt;
	private static Statement stmt;

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		Class.forName("org.h2.Driver");
		Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
		
		String sql = "drop table if exists member_test";
		stmt = conn.createStatement();
		stmt.execute(sql);
		String sql2 = "CREATE TABLE member_test("
				+ "NUM INT PRIMARY KEY, "
				+ "ID VARCHAR, "
				+ "PASSWORD VARCHAR, "
				+ "AGE INT,"
				+ "PHONE VARCHAR,"
				+ "MILEAGE INT);"; 
		Statement stmt = conn.createStatement();
		stmt.execute(sql2);
				
		pstmt = conn.prepareStatement("show tables");
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			System.out.println(rs.getObject(1));		
		}
		
		// add application code here
		conn.close();
	}
}
