package controller;

import java.io.IOException;

import entity.User;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter(urlPatterns = { "/*" })
public class RoleFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		HttpSession session = request.getSession(false);
		String uri = request.getRequestURI();
		if (uri.endsWith("/login")) {
			chain.doFilter(req, res);
			return;
		}
		if (session == null || session.getAttribute("account") == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}

		User user = (User) session.getAttribute("account");
		int roleId = user.getRoleid(); 
		boolean allowed = false;
		System.out.println("URI: " + uri + ", Role ID: " + roleId);
		if (uri.contains("/admin/") ){
			allowed = (roleId == 3); 
		} else if (uri.contains("/user/")) {
			allowed = (roleId == 1);
		} 
		else if (uri.contains("/manager/")) {
			allowed = (roleId == 2);
		} else {
			allowed = true;
		}

		if (allowed) {
			chain.doFilter(req, res);
		} else {
			response.sendError(HttpServletResponse.SC_FORBIDDEN, "Bạn không có quyền truy cập vào đường dẫn này.");
		}
	}

}
