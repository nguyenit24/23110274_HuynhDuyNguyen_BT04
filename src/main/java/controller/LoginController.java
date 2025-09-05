package controller;

import java.io.IOException;

import entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.IUserService;
import service.impl.UserServiceImpl;

/**
 * Servlet implementation class LoginController
 */

@WebServlet(urlPatterns = "/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		if(session != null && session.getAttribute("account") != null) {
			response.sendRedirect(request.getContextPath() + "/watting");
			return;
		}
		
		try {
			Cookie[] cookies = request.getCookies();
			if(cookies != null) {
				for(Cookie cookie : cookies) {
					if(cookie.getName().equals("account")) {
						session = request.getSession();
						session.setAttribute("account", cookie.getValue());
						response.sendRedirect(request.getContextPath() + "/watting");
						return;
					}
				}
			}
			request.getRequestDispatcher("/views/login.jsp").forward(request, response);
			
		} catch (Exception e) {
			request.getRequestDispatcher("/views/login.jsp").forward(request, response);
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String account = request.getParameter("account");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String alertMsg = "";
		if (username.isEmpty() || password.isEmpty()) {
			alertMsg = "Tài khoản hoặc mật khẩu không được rỗng";
			request.setAttribute("alert", alertMsg);
			request.getRequestDispatcher("/views/login.jsp").forward(request, response);
			return;
		}
		IUserService service = new UserServiceImpl();
		User user = service.login(username, password);
		if (user != null) {
			HttpSession session = request.getSession(true);
			session.setAttribute("account", user);
			response.sendRedirect(request.getContextPath() + "/waiting");
		} else {
			alertMsg = "Tài khoản hoặc mật khẩu không đúng";
			request.setAttribute("alert", alertMsg);
			request.getRequestDispatcher("/views/login.jsp").forward(request, response);
		}
	}

}
