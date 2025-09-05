package service;

import entity.User;

public interface IUserService {
	User login(String username, String password);
	User get(String username);
	void insert(User user);
	boolean register(String email, String password, String username);
	boolean checkExistEmail(String email);
	boolean checkExistUsername(String username);
	boolean updatePass(String email, String newPass);
}
