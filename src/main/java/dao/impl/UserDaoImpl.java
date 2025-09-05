package dao.impl;

import config.JPAConfig;
import dao.IUserDao;
import entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

public class UserDaoImpl implements IUserDao {

	@Override
	public User get(String username) {
		EntityManager em = JPAConfig.em();
		User user = null;
		try {
			TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.userName = :username", User.class);
			query.setParameter("username", username);
			user = query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			em.close();
		}
		return user;
	}

	@Override
	public boolean checkExistEmail(String email) {
		EntityManager em = JPAConfig.em();
		try {
			TypedQuery<Long> query = em.createQuery("SELECT COUNT(u) FROM User u WHERE u.email = :email", Long.class);
			query.setParameter("email", email);
			return query.getSingleResult() > 0;
		} finally {
			em.close();
		}
	}

	@Override
	public boolean checkExistUsername(String username) {
		EntityManager em = JPAConfig.em();
		try {
			TypedQuery<Long> query = em.createQuery("SELECT COUNT(u) FROM User u WHERE u.userName = :username", Long.class);
			query.setParameter("username", username);
			return query.getSingleResult() > 0;
		} finally {
			em.close();
		}
	}

	@Override
	public boolean updatePass(String email, String newPass) {
		EntityManager em = JPAConfig.em();
		try {
			em.getTransaction().begin();

			TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class);
			query.setParameter("email", email);

			User user = query.getSingleResult();
			if (user != null) {
				user.setPassWord(newPass);
				em.merge(user);
				em.getTransaction().commit();
				return true;
			}
			return false;
		} catch (NoResultException e) {
			return false;
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw e;
		} finally {
			em.close();
		}
	}

}
