package br.exercise.BO;

import org.apache.log4j.Logger;

import br.exercise.Dao.GiveBackMovieDao;
import br.exercise.Dao.MovieDao;
import br.exercise.bean.GiveBackMovie;
import br.exercise.bean.Movies;

public class SaveGiveBackMovie {
	private static Logger logger = Logger.getLogger(SaveGiveBackMovie.class);
	
	public SaveGiveBackMovie() {
		super();
		// TODO Auto-generated constructor stub
	}

	public boolean save(GiveBackMovie giveBackMovie) {
		try {
			GiveBackMovieDao giBackMovieDao = new GiveBackMovieDao();
			boolean ischange=changeMovieQtd(getMovies(giveBackMovie.getMovieId()));
			if(ischange){
				giBackMovieDao.save(giveBackMovie);
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
			if(movie.getQtdCopy()>=0){
				movie.setQtdCopy(movie.getQtdCopy()+1);
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
