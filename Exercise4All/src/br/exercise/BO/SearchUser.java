package br.exercise.BO;

import org.apache.log4j.Logger;

import br.exercise.Dao.UserDao;
import br.exercise.bean.User;

public class SearchUser {
	private static Logger logger = Logger.getLogger(SearchUser.class);
	public SearchUser() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public User searchByToken(String token){
		User user=new User();
		try {
			UserDao userDao=new UserDao();
			user=userDao.serchByToken(token);
		} catch (Exception e) {
			logger.error(e);
		}
		return user;
	}

}
