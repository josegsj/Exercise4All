package br.exercise.BO;

import java.util.List;

import org.apache.log4j.Logger;

import br.exercise.Dao.MovieDao;
import br.exercise.bean.Movies;

public class SearchMovies {
	private static Logger logger = Logger.getLogger(SearchMovies.class);

	
	public List<Movies> getListMoviesAvailable(){
		try {
			MovieDao movieDao= new MovieDao();
			return movieDao.listMovies();
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}
	
	
	public List<Movies> getListMoviesByTitle(String title){
		try {
			MovieDao movieDao= new MovieDao();
			return movieDao.listMovieByTiltle(title);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

}
