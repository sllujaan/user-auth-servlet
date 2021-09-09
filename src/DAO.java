import java.sql.*;

public class DAO {
	
	public void init() throws SQLException, ClassNotFoundException {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql:// localhost:3306/servlet","root","root");
		Statement st = con.createStatement();
		String sql = "select * from users";
		ResultSet rs = st.executeQuery(sql);
		while(rs.next()) {
			System.out.println(rs.getString("username") + " " + rs.getString("password"));
		}
		
		con.close();
		
	}
	
	public Connection initConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		return DriverManager.getConnection("jdbc:mysql:// localhost:3306/servlet","root","root");
	}
	
	public boolean verifyUser(String username, String password) throws ClassNotFoundException, SQLException {
		//init connection
		Connection con = this.initConnection();
		
		//verify user
		Statement st = con.createStatement();
		String sql = "select * from users where username = '" + username + "' and password = '" + password + "';";
		ResultSet rs = st.executeQuery(sql);
		while(rs.next()) {
			System.out.println(rs.getString("username") + " " + rs.getString("password"));
			return true;
		}
		//close the connection
		con.close();
		return false;
	}
}
