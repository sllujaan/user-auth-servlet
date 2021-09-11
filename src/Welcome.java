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
	
	
	public void getSession(HttpServletRequest req, HttpServletResponse res) throws IOException {
		Cookie rck[] = req.getCookies();
		
		if(rck != null) {
			for(int i=0; i < rck.length; i++) {
				System.out.println("-------------------------");
				System.out.println("<br>"+rck[i].getName()+" "+rck[i].getValue());
				System.out.println("-------------------------");
			}
		}
		
		HttpSession sess = req.getSession(false);
		System.out.println(sess.getAttribute(sess.getId()));
		System.out.println(sess.getId());
		
	}
	
	public String getkeyValueFromCookies(HttpServletRequest req, String key) throws IOException {
		Cookie rck[] = req.getCookies();
		if(rck == null) return "";
		
		for(int i=0; i < rck.length; i++) {
			if(rck[i].getName() == key) return rck[i].getValue();
		}
		
		return "";
	}
	
	
	
	public boolean verifySession(HttpServletRequest req, HttpServletResponse res) throws IOException {
		this.getSession(req, res);
		
		HttpSession sess = req.getSession(false);
		String sessUUID = (String) sess.getAttribute(sess.getId());
		sess.getAttribute(sessUUID);
		String userId = getkeyValueFromCookies(req, "username");
		
		if(userId == "") return false;
		
		
		
		return true;
	}
	
	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		PrintWriter out = res.getWriter();
		
		this.verifySession(req, res);
		
		ServletContext sc = getServletContext();
		String appVersion = sc.getInitParameter("appVersion");
		String userName = req.getParameter("username");
		
		Util.setResponse(res, 200);
		out.println("<h1>Welcome " + userName+"</h1>");
		out.println("<p>Version: " + appVersion +"</p>");
		
	}
	
}
