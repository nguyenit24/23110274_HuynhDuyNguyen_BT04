package controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import entity.Category;
import entity.User;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import service.ICategoryService;
import service.impl.CategoryService;
import util.Constant;


@WebServlet(urlPatterns = {"/manager/home"})
public class ManagerController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		String uri = req.getRequestURI();
		User user = (User)session.getAttribute("account");
		ICategoryService categoryService = new CategoryService();
		if(uri.contains("manager/home")) {
			List<Category> categories = categoryService.findAllByUserId(user.getId());
			req.setAttribute("categories", categories);
			req.getRequestDispatcher("/views/admin/category/list.jsp").forward(req, resp);
		} else if (uri.contains("/category/add")) {
			req.getRequestDispatcher("/views/admin/category/add.jsp").forward(req, resp);
		} else if (uri.contains("/category/edit")) {
			int id = Integer.parseInt(req.getParameter("id"));
			Category category = categoryService.findById(id);
			if (category.getUser_id() != user.getId()) {
				req.setAttribute("error", "You do not have permission to edit this category.");
				req.getRequestDispatcher("/manager/home").forward(req, resp);
				return;
			}
			req.setAttribute("category", category);	
			req.getRequestDispatcher("/views/admin/category/edit.jsp").forward(req, resp);
		} else if (uri.contains("/category/delete")) {
			int id = Integer.parseInt(req.getParameter("id"));
			Category category = categoryService.findById(id);
			if (category.getUser_id() != user.getId()) {
				req.setAttribute("error", "You do not have permission to delete this category.");
				req.getRequestDispatcher("/manager/home").forward(req, resp);
				return;
			}
			categoryService.delete(id);
			resp.sendRedirect(req.getContextPath() + "/manager/home");
		} else {
			resp.sendRedirect(req.getContextPath() + "/login");
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
	}
	
}
