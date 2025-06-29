package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


/*ログイン情報を判定するクラス*/
public class CheckLogin {
	public Optional<Map<String, String>> check(String email, String pass) {
		String sql = "SELECT * FROM user WHERE email = ? AND pass = ?;";
		Map<String, String> sqlResultMap = new HashMap<>();
		Optional<Map<String, String>> checkResult;
		try(Connection con = DBconnecter.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);){
			pstmt.setString(1, email);
			pstmt.setString(2, pass);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				sqlResultMap.put("name",rs.getString("name"));
				sqlResultMap.put("userid", rs.getString("id"));
				checkResult = Optional.of(sqlResultMap);
				return checkResult;
			}else {
				return checkResult = Optional.ofNullable(null);			}
		}catch(SQLException e) {
			System.out.println(e);
		}
			return checkResult = Optional.ofNullable(null);
	}
}
