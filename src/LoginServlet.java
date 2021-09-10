import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LoginServlet extends HttpServlet {
	
	public void setCookies(HttpServletRequest req, HttpServletResponse res, String userName) throws IOException {
		Cookie rck[] = req.getCookies();
		
		if(rck != null) {
			for(int i=0; i < rck.length; i++) {
				System.out.println("<br>"+rck[i].getName()+" "+rck[i].getValue());
			}
		}
		
		Cookie ck = new Cookie("username", userName);
		res.addCookie(ck);
	}

	
	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		PrintWriter out = res.getWriter();
		
		String userName = req.getParameter("username");
		String password = req.getParameter("password");
		DAO dao = new DAO();
		
		try {
			boolean userFound = dao.verifyUser(userName, password);
			if(!userFound) {
				req.getRequestDispatcher("user-not-found").forward(req, res);
				return;
			}
			
			
			res.sendRedirect("welcome");
			this.setCookies(req, res, userName);
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("doGet Called.");
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("doPost Called.");
	}
}
