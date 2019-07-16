package br.exercise.Dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;
import org.hibernate.query.Query;

import br.exercise.bean.Movies;

public class MovieDao {
	
	private static Logger logger = Logger.getLogger(MovieDao.class);

	public MovieDao() {
		super();
		// TODO Auto-generated constructor stub
	}

	public boolean update(Movies Movies) {	
		EntityManagerFactory emf= null;
		EntityManager em=null;
		try {
			emf=Persistence.createEntityManagerFactory("Exercise4All");
			em=emf.createEntityManager();
			em.merge(Movies);
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
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Movies> listMovies() {
		EntityManagerFactory emf= null;
		EntityManager em=null;
		List<Movies> listMovie= new ArrayList<Movies>();
		try {	
			emf=Persistence.createEntityManagerFactory("Exercise4All");
			em=emf.createEntityManager();
			StringBuilder hql = new StringBuilder();
			hql.append("select new Movies( m.movie_id,m.title, m.qtd_copy ) from movies m  where m.qtd_copy>0");

			Query query = (Query) em.createQuery(hql.toString());
			listMovie=query.getResultList();
		} catch (Exception e) {
			logger.error(e);
		}finally {
			em.close();
			emf.close();
		}
        return listMovie;
		
	}
	
	 @SuppressWarnings("unchecked")
	public List<Movies> listMovieByTiltle(String title) {
		EntityManagerFactory emf= null;
		EntityManager em=null;
		List<Movies> listMovies = new ArrayList<Movies>();
		try {	
			emf=Persistence.createEntityManagerFactory("Exercise4All");
			em=emf.createEntityManager();
			StringBuilder hql = new StringBuilder();
			hql.append("select new Movies( m.movie_id,m.title, m.qtd_copy ) from movies m  where m.title:=titleMovie");
			Query<Movies> query = (Query<Movies>) em.createQuery(hql.toString())
				.setParameter("titleMovie", title);
			listMovies= query.getResultList();
		} catch (Exception e) {
			logger.error(e);
		}finally {
			em.close();
			emf.close();
		}	
		return listMovies;
	
	}
	
	 public Movies getById( int MoviedId) {
		EntityManagerFactory emf= null;
		EntityManager em=null;
		try {	
			emf=Persistence.createEntityManagerFactory("Exercise4All");
			em=emf.createEntityManager();
		}catch (Exception e) {
			logger.error(e);
		}finally {
			em.close();
			emf.close();
		}	
        return (Movies) em.find(Movies.class, MoviedId);
       }
	
	

}
