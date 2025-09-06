package controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import entity.Category;
import entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import service.ICategoryService;
import service.impl.CategoryService;
import util.Constant;

/**
 * Servlet implementation class HomeController
 */
@MultipartConfig(
	    fileSizeThreshold = 1024 * 1024 * 2, // 2MB
	    maxFileSize = 1024 * 1024 * 10,      // 10MB
	    maxRequestSize = 1024 * 1024 * 50 )
@WebServlet(urlPatterns = { "/user/home", "/admin/home", "/category/add",
		"/category/edit", "/category/delete" })
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String uri = request.getRequestURI();
		User user = (User) session.getAttribute("account");
		ICategoryService categoryService = new CategoryService();
		if (uri.endsWith("/home")) {
			List<Category> categories = categoryService.findAll();
			request.setAttribute("categories", categories);
			request.getRequestDispatcher("/views/admin/category/list.jsp").forward(request, response);
		} else if (uri.contains("/category/add")) {
			request.getRequestDispatcher("/views/admin/category/add.jsp").forward(request, response);
		} else if (uri.contains("/category/edit")) {
			int id = Integer.parseInt(request.getParameter("id"));
			Category category = categoryService.findById(id);
			if (category.getUser_id() != user.getId()) {
				request.setAttribute("error", "You do not have permission to edit this category.");
				if(user.getRoleid() == 1) {
					request.getRequestDispatcher("/user/home").forward(request, response);
				} else {
					request.getRequestDispatcher("/admin/home").forward(request, response);
				}
				return;
			}
			request.setAttribute("category", category);
			request.getRequestDispatcher("/views/admin/category/edit.jsp").forward(request, response);
		} else if (uri.contains("/category/delete")) {
			int id = Integer.parseInt(request.getParameter("id"));
			Category category = categoryService.findById(id);
			if (category.getUser_id() != user.getId()) {
				request.setAttribute("error", "You do not have permission to delete this category.");
				if(user.getRoleid() == 1) {
					request.getRequestDispatcher("/user/home").forward(request, response);
				} else {
					request.getRequestDispatcher("/admin/home").forward(request, response);
				}
				return;
			}
			categoryService.delete(id);
			if(user.getRoleid() == 1) {
				request.getRequestDispatcher("/user/home").forward(request, response);
			} else {
				request.getRequestDispatcher("/admin/home").forward(request, response);
			}
		} else {
			response.sendRedirect(request.getContextPath() + "/login");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String uri = request.getRequestURI();
		User user = (User) session.getAttribute("account");
		ICategoryService categoryService = new CategoryService();
		if (uri.contains("add")) {
			String name = request.getParameter("categoryname");
			Category category = new Category();
			category.setCategoryname(name);
			category.setImages("1.jpg");
			category.setUser_id(user.getId());
			String fname = "";
			String uploadPath = Constant.upload;
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists()) {
				uploadDir.mkdir();
			}
			try {
				Part part = request.getPart("images");
				if(part != null && part.getSize() > 0) {
					fname = part.getSubmittedFileName();
					int index = fname.lastIndexOf(".");
					String ext = fname.substring(index);
					fname = System.currentTimeMillis() + ext;
					part.write(uploadPath + File.separator + fname);
				    System.out.print(fname);
					category.setImages(fname);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			categoryService.insert(category);
			if(user.getRoleid() == 1) {
				response.sendRedirect(request.getContextPath() + "/user/home");
			} else if (user.getRoleid() == 3) {
				response.sendRedirect(request.getContextPath() + "/admin/home");
			} else {
				response.sendRedirect(request.getContextPath() + "/manager/home");
			}
			
		} else if (uri.contains("edit")) {
			int id = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("categoryname");
			Category category = categoryService.findById(id);
			if (category != null) {
				category.setCategoryname(name);
				String fname = "";
	            String uploadPath = Constant.upload;
	            File uploadDir = new File(uploadPath);
	            if(!uploadDir.exists()) {
	                uploadDir.mkdir();
	            }
	            try {
	                Part filePart = request.getPart("images");
	                if(filePart.getSize() > 0) {
	                    fname = filePart.getSubmittedFileName();
	                    int index = fname.lastIndexOf(".");
	                    String ext = fname.substring(index);
	                    fname = System.currentTimeMillis() + ext;
	                    filePart.write(uploadPath + File.separator + fname);
	                    System.out.print(fname);
	                    category.setImages(fname);
	                } 
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
				categoryService.update(category);
				if(user.getRoleid() == 1) {
					response.sendRedirect(request.getContextPath() + "/user/home");
				} else if (user.getRoleid() == 3) {
					response.sendRedirect(request.getContextPath() + "/admin/home");
				} else {
					response.sendRedirect(request.getContextPath() + "/manager/home");
				}
			}
		} 
	}

}
