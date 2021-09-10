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


public class Welcome extends HttpServlet {
	
	
	public void getCookies(HttpServletRequest req, HttpServletResponse res) throws IOException {
		Cookie rck[] = req.getCookies();
		
		if(rck != null) {
			for(int i=0; i < rck.length; i++) {
				System.out.println("<br>"+rck[i].getName()+" "+rck[i].getValue());
			}
		}
	}
	
	
	
	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		PrintWriter out = res.getWriter();
		
		ServletContext sc = getServletContext();
		String appVersion = sc.getInitParameter("appVersion");
		String userName = req.getParameter("username");
		
		Util.setResponse(res, 200);
		out.println("<h1>Welcome " + userName+"</h1>");
		out.println("<p>Version: " + appVersion +"</p>");
		
	}
	
}
