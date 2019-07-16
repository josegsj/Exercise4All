package br.exercise.Dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;

import br.exercise.bean.GiveBackMovie;


public class GiveBackMovieDao {
	
	private static Logger logger = Logger.getLogger(GiveBackMovieDao.class);

	
	public GiveBackMovieDao() {
			super();
			// TODO Auto-generated constructor stub
		}

	public boolean save(GiveBackMovie giveBackMovie) {
			EntityManagerFactory emf= null;
			EntityManager em=null;
			try {	
				emf=Persistence.createEntityManagerFactory("Exercise4All");
				em=emf.createEntityManager();
				em.persist(giveBackMovie);
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
