//package controller;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.List;
//
//import org.hibernate.Session;
//
//import entity.Category;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.MultipartConfig;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//import jakarta.servlet.http.Part;
//import service.ICategoryService;
//import service.impl.CategoryService;
//import util.Constant;
//
///**
// * Servlet implementation class CategoryController
// */
//@MultipartConfig(
//	    fileSizeThreshold = 1024 * 1024 * 2, // 2MB
//	    maxFileSize = 1024 * 1024 * 10,      // 10MB
//	    maxRequestSize = 1024 * 1024 * 50 )
////@WebServlet(urlPatterns = { "/admin/categories", "/admin/category/add", "/admin/category/edit",
////		"/admin/category/delete" })
//public class CategoryController extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//	private HttpSession session = null;
//	/**
//	 * @see HttpServlet#HttpServlet()
//	 */
//	public CategoryController() {
//		super();
//		// TODO Auto-generated constructor stub
//	}
//
//	/**
//	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		session = request.getSession(false);
//		ICategoryService categoryService = new CategoryService();
//		String uri = request.getRequestURI();
//		if (uri.contains("categories")) {
//			List<Category> categories = categoryService.findAll();
//			request.setAttribute("categories", categories);
//			request.getRequestDispatcher("/views/admin/category/list.jsp").forward(request, response);
//		} else if (uri.contains("add")) {
//			request.getRequestDispatcher("/views/admin/category/add.jsp").forward(request, response);
//		} else if (uri.contains("edit")) {
//			int id = Integer.parseInt(request.getParameter("id"));
//			Category category = categoryService.findById(id);
//			request.setAttribute("category", category);
//			request.getRequestDispatcher("/views/admin/category/edit.jsp").forward(request, response);
//		} else if (uri.contains("delete")) {
//			int id = Integer.parseInt(request.getParameter("id"));
//			categoryService.delete(id);
//			response.sendRedirect(request.getContextPath() + "/admin/categories");
//		} else {
//			response.sendError(HttpServletResponse.SC_NOT_FOUND);
//		}
//	}
//
//	/**
//	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
//	 *      response)
//	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		ICategoryService categoryService = new CategoryService();
//		String uri = request.getRequestURI();
//		if (uri.contains("add")) {
//			String name = request.getParameter("categoryname");
//			Category category = new Category();
//			category.setCategoryname(name);
//			category.setImages("1.jpg");
//			String fname = "";
//			String uploadPath = Constant.upload;
//			File uploadDir = new File(uploadPath);
//			if (!uploadDir.exists()) {
//				uploadDir.mkdir();
//			}
//			try {
//				Part part = request.getPart("images");
//				if(part != null && part.getSize() > 0) {
//					fname = part.getSubmittedFileName();
//					int index = fname.lastIndexOf(".");
//					String ext = fname.substring(index);
//					fname = System.currentTimeMillis() + ext;
//					part.write(uploadPath + File.separator + fname);
//				    System.out.print(fname);
//					category.setImages(fname);
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			categoryService.insert(category);
//			response.sendRedirect(request.getContextPath() + "/admin/categories");
//		} else if (uri.contains("edit")) {
//			int id = Integer.parseInt(request.getParameter("id"));
//			String name = request.getParameter("categoryname");
//			Category category = categoryService.findById(id);
//			if (category != null) {
//				category.setCategoryname(name);
//				String fname = "";
//	            String uploadPath = Constant.upload;
//	            File uploadDir = new File(uploadPath);
//	            if(!uploadDir.exists()) {
//	                uploadDir.mkdir();
//	            }
//	            try {
//	                Part filePart = request.getPart("images");
//	                if(filePart.getSize() > 0) {
//	                    fname = filePart.getSubmittedFileName();
//	                    int index = fname.lastIndexOf(".");
//	                    String ext = fname.substring(index);
//	                    fname = System.currentTimeMillis() + ext;
//	                    filePart.write(uploadPath + File.separator + fname);
//	                    System.out.print(fname);
//	                    category.setImages(fname);
//	                } 
//	            } catch (Exception e) {
//	                e.printStackTrace();
//	            }
//				categoryService.update(category);
//				response.sendRedirect(request.getContextPath() + "/admin/categories");
//			}
//		} 
//	}
//
//}
