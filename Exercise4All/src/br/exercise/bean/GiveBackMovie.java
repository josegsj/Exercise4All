package br.exercise.bean;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
@Table(name="give_back_movie")
public class GiveBackMovie {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="give_id")
	private int giveId;
	@Column(name="user_id")
	private int userId;
	@Column(name="movie_id")
	private int movieId;
	@Column(name="date")
	@Temporal(TemporalType.DATE)
	private Calendar date;
	
	public GiveBackMovie() {
		super();
	}
	public int getGiveId() {
		return giveId;
	}
	public void setGiveId(int givetId) {
		this.giveId = givetId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getMovieId() {
		return movieId;
	}
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	public Calendar getDate() {
		return date;
	}
	public void setDate(Calendar date) {
		this.date = date;
	}

	
	
	
}
