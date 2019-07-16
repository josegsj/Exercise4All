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
@Table(name="rent_movie")
public class RentMovie {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="rent_id")
	private int rentId;
	@Column(name="user_id")
	private int userId;
	@Column(name="movie_id")
	private int movieId;
	@Column(name="date")
	@Temporal(TemporalType.DATE)
	private Calendar date;
	
	public RentMovie() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getRentId() {
		return rentId;
	}
	public void setRentId(int rentId) {
		this.rentId = rentId;
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
