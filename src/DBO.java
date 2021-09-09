import java.sql.*;

public class DBO {
	
	public void init() throws SQLException, ClassNotFoundException {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql:// localhost:3306/servlet","root","root");
		Statement st = con.createStatement();
		String sql = "select * from users";
		ResultSet rs = st.executeQuery(sql);
		while(rs.next()) {
			System.out.println(rs.getString("username") + " " + rs.getString("password"));
		}
		
	}
}
