package poly.interceptor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class SecurityInterceptor5 extends HandlerInterceptorAdapter{
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse respond,
			Object hanler) throws IOException {
		
		HttpSession session = request.getSession();
		if((session.getAttribute("user3") == null) && (session.getAttribute("user2") == null)) {
			respond.sendRedirect(request.getContextPath() + "/login/user5.htm");
			return false;
		}
		
		return true;
	}
}
