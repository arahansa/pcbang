package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import asset.DBConnectionMgr;

public class DaoLogin {
	
	public static void main(String[] args) throws Exception {
		H2DB_Initializer h2db = new H2DB_Initializer();
		h2db.initDatabase();
		DaoLogin loginTest = new DaoLogin();
		int test2 = loginTest.insertUser();
		boolean test = loginTest.loginCheck("test", "1234");
		System.out.println("로그인 결과 :" + test + "생성결과 :" + test2);
	}

	public  int insertUser() throws Exception {
		DBConnectionMgr pool = DBConnectionMgr.getInstance();

		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;

		con = pool.getConnection();
		sql = "select * from member_test where id='test'";
		pstmt = con.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		if(!rs.next()){
			System.out.println("비어있는 값이군!?");
			sql = "insert into member_test (id, password) values ('test', '1234');";
			pstmt = con.prepareStatement(sql);
			return pstmt.executeUpdate();
		}
		return 0;
	}

	public  boolean loginCheck(String id, String password) {
		boolean flag = false;

		DBConnectionMgr pool = DBConnectionMgr.getInstance();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		String getPass = null;

		try {
			con = pool.getConnection();

			// 문장생성
			sql = "select password from member_test where id=?";
			// 문장연결, 열차준비
			pstmt = con.prepareStatement(sql);
			// 빈칸채워주기
			pstmt.setString(1, id);
			// 실행, 열차출바알~!
			rs = pstmt.executeQuery();
			if (rs.next()) {
				// 패스워드를 읽어온다.
				getPass = rs.getString("password");
				// 데이터베이스에서 읽어온 문자열과 사용자가 입력한 비밀번호가 같을 경우에는
				// 참을 반환을 하겠죠?
				if (getPass.equals(password)) {
					System.out.println("받아온 비밀번호 : " + getPass);
					flag = true;
				}
			}
		} catch (Exception e) {
			// 원래 예외처리는 크게 잡으면 안되고, 따로 처리가 되어야 합니다.
			e.printStackTrace();
		} finally {
			// 자원반납
			pool.freeConnection(con, pstmt, rs);
		}
		// 결과값 반납
		return flag;
	}
}