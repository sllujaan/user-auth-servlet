import javax.servlet.http.HttpServletResponse;

public class Util {
	static public void setResponse(HttpServletResponse res, int status) {
		res.setContentType("text/html");
		res.setStatus(status);
	}
}
