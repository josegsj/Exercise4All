package br.exercise.BO;

import org.apache.log4j.Logger;

import br.exercise.Dao.UserDao;
import br.exercise.bean.User;

public class SaveUser {
	private static Logger logger = Logger.getLogger(SaveUser.class);

	public boolean save(User user) {
		try {
			UserDao userDao=new UserDao();
			if(user.getLogin()!=null && user.getPassword()!=null && user.getName()!=null) {
				if(validateUserIsTheSame(user)) {
					userDao.save(user);
					return true;
				}
			}
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
		return false;
	}
	
	public boolean update(User user) {
		try {
			UserDao userDao=new UserDao();
			if(user.getLogin()!=null && user.getPassword()!=null && user.getName()!=null) {
				userDao.update(user);
				return true;
			}
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
		return false;
	}

	
	public boolean validateUserIsTheSame(User user) {
		try {
			UserDao userDao=new UserDao();
			User newUder=userDao.serch(user.getLogin(), user.getPassword());
			if(newUder!=null) {
				return true;
			}
		}catch (Exception e) {
			logger.error(e);
		}
		return false;
	}
}
