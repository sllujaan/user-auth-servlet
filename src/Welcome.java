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


public class Welcome extends HttpServlet {
	
	private String userName = "-";
	
	public String getkeyValueFromCookies(HttpServletRequest req, String key) throws IOException {
		Cookie rck[] = req.getCookies();
		if(rck == null) return "";
		
		for(int i=0; i < rck.length; i++) {
			if(rck[i].getName().trim().equals(key)) {
				userName = rck[i].getValue();
				return rck[i].getValue();
			}
		}
		
		return "";
	}
	
	
	
	public boolean verifySession(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		HttpSession sess = req.getSession(false);
		if(sess == null) return false;
		
		String sessUUID = (String) sess.getAttribute(sess.getId());
		String userIdSess = (String) sess.getAttribute(sessUUID);
		String userId = getkeyValueFromCookies(req, "username");
		
		if(userId.trim().equals("")) return false;
		if(!userId.trim().equals(userIdSess)) return false;
		
		return true;
	}
	
	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		PrintWriter out = res.getWriter();
		
		if(!verifySession(req, res)) {
			System.out.println("session verification failed!");
			req.getRequestDispatcher("unauthorized-user").forward(req, res);
		}
		
		ServletContext sc = getServletContext();
		String appVersion = sc.getInitParameter("appVersion");
		
		Util.setResponse(res, 200);
		out.println("<h1>Welcome " + userName+"</h1>");
		out.println("<p>Version: " + appVersion +"</p>");
		
	}
	
}
