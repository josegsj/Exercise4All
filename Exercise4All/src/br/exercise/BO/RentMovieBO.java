package br.exercise.BO;

import org.apache.log4j.Logger;

import br.exercise.Dao.MovieDao;
import br.exercise.Dao.RentMovieDao;
import br.exercise.bean.Movies;
import br.exercise.bean.RentMovie;

public class RentMovieBO {
	private static Logger logger = Logger.getLogger(RentMovieBO.class);
	
	public RentMovieBO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public boolean save(RentMovie rentMovie) {
		try {
			RentMovieDao rentMovieDao = new RentMovieDao();
			boolean ischange=changeMovieQtd(getMovies(rentMovie.getMovieId()));
			if(ischange){
				rentMovieDao.save(rentMovie);
				return true;
			}
		}catch(Exception e){
			logger.error(e);		
		}
		return false;
	}
	
	public boolean changeMovieQtd(Movies movie){
		try {
			MovieDao movieDao=new MovieDao();
			if(movie.getQtdCopy()>0){
				movie.setQtdCopy(movie.getQtdCopy()-1);
				movieDao.update(movie);
				return true;
			}
		}catch(Exception e){
			logger.error(e);		
		}
		return false;
	}
	
	public Movies getMovies(int movieId){
		try {
			MovieDao movieDao=new MovieDao();
			return movieDao.getById(movieId);
		}catch(Exception e){
			logger.error(e);		
		}
		return null;
	}
	
}
