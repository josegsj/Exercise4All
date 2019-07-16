package br.exercise.Dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import br.exercise.bean.User;

public class UserDao {
	
	  private static SessionFactory factory; 
	  private static Logger logger = Logger.getLogger(UserDao.class);
	public UserDao() {
		super();
		// TODO Auto-generated constructor stub
	}
	public boolean save(User user) {
		EntityManagerFactory emf= null;
		EntityManager em=null;
		try {
			emf=Persistence.createEntityManagerFactory("Exercise4All");
			em=emf.createEntityManager();
			em.persist(user);
			em.getTransaction().commit();
		} catch (Exception e) {
			logger.error(e);
			return false;
		}finally {
			em.close();
			emf.close();
		
		}
		return true;
	}
	
	public boolean update(User user) {
		EntityManagerFactory emf= null;
		EntityManager em=null;
		try {	
			emf=Persistence.createEntityManagerFactory("Exercise4All");
			em=emf.createEntityManager();
			em.merge(user);
			em.getTransaction().commit();
		} catch (Exception e) {
			logger.error(e);
			return false;
		}finally {
			em.close();
			emf.close();
		}
		return true;
	}
	
	@SuppressWarnings("deprecation")
	public User serch(String login,String password) {
		User user=new User();
		try {	
			Session session =factory.openSession() ;
			Criteria crit =session.createCriteria(User.class);
			crit.add(Restrictions.eq("login",login));
			crit.add(Restrictions.eq("password",password));	
			user= (User) crit.list();
		}catch (Exception e) {
			logger.error(e);
		}
		return user;
	}
	
	@SuppressWarnings("deprecation")
	public User serchByToken(String token) {
		User user=new User();
		try {	
			Session session =factory.openSession() ;
			Criteria crit =session.createCriteria(User.class);
			crit.add(Restrictions.eq("token",token));
			user=(User) crit.list();
		}catch (Exception e) {
			logger.error(e);
		}
		return user;
	}

}
