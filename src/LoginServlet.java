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
import javax.servlet.http.HttpSession;


public class LoginServlet extends HttpServlet {
	
	public void setSession(HttpServletRequest req, HttpServletResponse res, String userName) throws IOException {
		HttpSession sess = req.getSession(true);
		sess.setAttribute(sess.getId(), "12345");
		sess.setAttribute("12345", userName);
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
			
			this.setSession(req, res, userName);
			res.sendRedirect("welcome");
			
			
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
