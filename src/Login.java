import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Login extends HttpServlet {
	
	public void handleCookies(HttpServletRequest req, HttpServletResponse res) throws IOException {
		Cookie rck[] = req.getCookies();
		
		if(rck != null) {
			for(int i=0; i < rck.length; i++) {
				System.out.println("<br>"+rck[i].getName()+" "+rck[i].getValue());
			}
		}
		
		Cookie ck = new Cookie("abc", "new values ---- ");
		res.addCookie(ck);
	}
	
	
	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException {
		PrintWriter out = res.getWriter();
		out.println("login");
		
		String userName = req.getParameter("username");
		String password = req.getParameter("password");
		DAO dao = new DAO();
		try {
			boolean userFound = dao.verifyUser(userName, password);
			if(!userFound) {
				out.println("user not found");
				return;
			}
			
			out.println("welcome " + userName);
			this.handleCookies(req, res);
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
