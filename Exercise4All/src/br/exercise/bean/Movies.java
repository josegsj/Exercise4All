package br.exercise.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="movies")
public class Movies {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="movie_id")
	private int movieId;
	@Column(name="title")
	private String title;
	@Column(name="qtd_copy")
	private int qtdCopy;
	
	public Movies() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getMovieId() {
		return movieId;
	}
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getQtdCopy() {
		return qtdCopy;
	}
	public void setQtdCopy(int qtdCopy) {
		this.qtdCopy = qtdCopy;
	}
	
	

}
