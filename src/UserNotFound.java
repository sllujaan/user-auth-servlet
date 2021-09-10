import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class UserNotFound extends HttpServlet {
	

	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		PrintWriter out = res.getWriter();
		
		String userName = req.getParameter("username");
		String password = req.getParameter("password");
		
		res.setContentType("text/html");
		res.setStatus(404);
		out.println("<h1>User Not Found</h1>");
		out.println("<p>" + userName + "</p>");
		
		
	}
	
}
