package service.impl;

import dao.IUserDao;
import dao.impl.UserDaoImpl;
import entity.User;
import service.IUserService;

public class UserServiceImpl implements IUserService {
	
	private IUserDao userDao = new UserDaoImpl();

	@Override
	public User login(String username, String password) {
		
		if(this.checkExistUsername(username) == false) {
			return null;
		}
		
		User user = userDao.get(username);
		if(user != null && user.getPassWord().equals(password)) {
			return user;
		}
		return null;
	}

	@Override
	public User get(String username) {
		return userDao.get(username);
	}

	@Override
	public void insert(User user) {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean register(String email, String password, String username) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean checkExistEmail(String email) {
		return userDao.checkExistEmail(email);
	}

	@Override
	public boolean checkExistUsername(String username) {
		return userDao.checkExistUsername(username);
	}

	@Override
	public boolean updatePass(String email, String newPass) {
		return userDao.updatePass(email, newPass);
	}

}
