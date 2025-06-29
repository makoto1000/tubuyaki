package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.jasper.tagplugins.jstl.core.Catch;

import jakarta.servlet.jsp.tagext.TryCatchFinally;
import model.DBconnecter;
import model.Tubuyaki;

public class GetTubuyakiListDao {
	String sql = "SELECT name, text, likes FROM tubuyaki INNSER JOIN user ON tubuyaki.userid = user.id";
	List<Tubuyaki> list = new ArrayList<>();
	Optional<List<Tubuyaki>> tubuyakiList;
	String userName;
	String text;
	int likes;
	
	public Optional<List<Tubuyaki>> getTubuyakiList() {
	try(Connection con = DBconnecter.getConnection();
		PreparedStatement pstmt = con.prepareStatement(sql);){
		
		ResultSet rs = pstmt.executeQuery();
		
		if(!(rs.next())) {
			while(rs.next()) {
				userName = rs.getString("name");
				text = rs.getString("text");
				likes = rs.getInt("likes");
				list.add(new Tubuyaki(userName, text, likes));
			}
		}else {
			return tubuyakiList = Optional.ofNullable(null);
		}
		
		
	}catch(SQLException e) {
		System.err.println(e);
	}
	
	return tubuyakiList = Optional.of(list);
	}
}
