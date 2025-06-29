package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.DBconnecter;

public class RegisterTubuyakiDao {

	
	public void registerTubuyaki(String userid, String tubuyaki) {
	
		String sql = "INSERT INTO tubuyaki(userid, text, likes) VALUES(?, ?, ?)";
		String likes = "0";
		try(Connection con = DBconnecter.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);){
			pstmt.setString(1, userid);
			pstmt.setString(2, tubuyaki);
			pstmt.setString(3, likes);
			pstmt.executeUpdate();
		}catch(SQLException e){
			System.out.println(e);
		}
	}
}
