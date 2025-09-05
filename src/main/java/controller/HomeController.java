package controller;

import java.io.IOException;
import java.util.List;

import entity.Category;
import entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.ICategoryService;
import service.impl.CategoryService;

/**
 * Servlet implementation class HomeController
 */

@WebServlet(urlPatterns = {"/manager/home", "/user/home" , "/admin/home"})
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		HttpSession session = request.getSession(false);
		User user = (User)session.getAttribute("account");
		ICategoryService categoryService = new CategoryService();
		if(uri.contains("manager")) {
			List<Category> categories = categoryService.findAllByUserId(user.getId());
			request.setAttribute("categories", categories);
			request.getRequestDispatcher("/views/admin/category/list.jsp").forward(request, response);
		} else if(uri.contains("user") || uri.contains("admin")) {
			List<Category> categories = categoryService.findAll();
			request.setAttribute("categories", categories);
			request.getRequestDispatcher("/views/admin/category/list.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
