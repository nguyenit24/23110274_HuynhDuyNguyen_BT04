package service.impl;

import java.util.List;

import dao.ICategoryDAO;
import dao.impl.CategoryDAO;
import entity.Category;
import service.ICategoryService;

public class CategoryService implements ICategoryService{

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

}
