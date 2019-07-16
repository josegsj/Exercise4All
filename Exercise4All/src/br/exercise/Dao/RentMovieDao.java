package br.exercise.Dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;

import br.exercise.bean.RentMovie;

public class RentMovieDao {
	private static Logger logger = Logger.getLogger(RentMovieDao.class);	
	
	public RentMovieDao() {
		super();
		// TODO Auto-generated constructor stub
	}

	public boolean save(RentMovie rentMovie) {
		EntityManagerFactory emf= null;
		EntityManager em=null;
		try {
			emf=Persistence.createEntityManagerFactory("Exercise4All");
			em=emf.createEntityManager();
			em.persist(rentMovie);
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
}
