package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


import model.DBconnecter;
import model.User;

public class UserRegisterDao {
	public void userRegister(User user) {
		String name = user.getName();
		String email = user.getEmail();
		String pass = user.getPass();
		
		String sql = "INSERT INTO user(name, email, pass) VALUES(?, ?, ?)";
		try(Connection con = DBconnecter.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);){
			pstmt.setString(1, name);
			pstmt.setString(2, email);
			pstmt.setString(3, pass);
			pstmt.executeUpdate();
		}catch(SQLException e) {
			System.out.println(e);
		}
	}
	}
