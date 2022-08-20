package login.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.stereotype.Repository;

import login.vo.MemberVO;

@Repository
public class LoginDAO implements LoginDAOInter{
	Connection conn;
	PreparedStatement pstmt;

	public LoginDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system", "1111");
		}catch(Exception e) {
			System.out.println("database connection error");
			e.printStackTrace();
		}
	}
	

	@Override
	public boolean login(MemberVO member) {
		String sql=null;
		ResultSet rs=null;
		
		try {
			sql="select * from member where id=? and password=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPassword());
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				return true;
			}
			
		}catch(Exception e) {
			System.out.println("login error");
			e.printStackTrace();
		}
		
		return false;
		
	}


	
}


