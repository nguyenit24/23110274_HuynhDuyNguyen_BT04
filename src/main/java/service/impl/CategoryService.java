package service.impl;

import java.util.List;

import dao.ICategoryDAO;
import dao.IUserDao;
import dao.impl.CategoryDAO;
import dao.impl.UserDaoImpl;
import entity.Category;
import service.ICategoryService;

public class CategoryService implements ICategoryService{
	
	IUserDao userDao = new UserDaoImpl();

	ICategoryDAO categoryDAO = new CategoryDAO();
	
	@Override
	public void insert(Category category) {
		categoryDAO.create(category);
	}

	@Override
	public List<Category> findAll() {
		return categoryDAO.findAll();
	}

	@Override
	public void update(Category category) {
		if(categoryDAO.findById(category.getId()) != null) {
			categoryDAO.update(category);
		}
		else {
			throw new RuntimeException("Category not found");
		}
	}

	@Override
	public void delete(int id) {
		if(categoryDAO.findById(id) != null) {
			categoryDAO.delete(id);
		}
		else {
			throw new RuntimeException("Category not found");
		}
	}

	@Override
	public Category findById(int id) {
		return categoryDAO.findById(id);
	}

	@Override
	public List<Category> findAllByUserId(int userId) {
		return categoryDAO.findAllByUserId(userId);
	}

}
