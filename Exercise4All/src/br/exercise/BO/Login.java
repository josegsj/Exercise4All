package br.exercise.BO;

import org.apache.log4j.Logger;

import br.exercise.Dao.UserDao;
import br.exercise.bean.User;

public class Login {
	private static Logger logger = Logger.getLogger(Login.class);
	
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User Login(String login,String password) {
		try {
			UserDao userDao=new UserDao();
			User user=userDao.serch(login, password);
			if(user!=null) {
				return user;
			}
		}catch(Exception e){
			logger.error(e);		
		}
			return null;
	}
	


}
