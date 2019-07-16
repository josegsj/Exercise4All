package br.exercise.service;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.exercise.BO.Login;
import br.exercise.BO.RentMovieBO;
import br.exercise.BO.SaveGiveBackMovie;
import br.exercise.BO.SaveUser;
import br.exercise.BO.SearchMovies;
import br.exercise.BO.SearchUser;
import br.exercise.bean.GiveBackMovie;
import br.exercise.bean.Movies;
import br.exercise.bean.RentMovie;
import br.exercise.bean.User;

import io.jsonwebtoken.SignatureAlgorithm;


import java.io.UnsupportedEncodingException;
import io.jsonwebtoken.*;
 

@Path("/")
public class MovieRentalServices {
	
	@POST
	 @Produces(MediaType.APPLICATION_JSON)
	 @Path("/saveUser")
	public Response saveUser(@PathParam("login")String login,@PathParam("password")String password,@PathParam("name")String name) {
		
		try {
		SaveUser saveuser = new SaveUser();
		User user = new User();
		user.setName(name);
		user.setLogin(login);
		user.setPassword(password);
		saveuser.save(user);
		} catch (Exception e) {
			 throw new WebApplicationException(404);
		}
		 return Response.ok( "usuario salvo com sucesso!!!" ).build();

	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/login")
	public Response login(@PathParam("login") String login,@PathParam("password") String password) {
			try {
				Login loginBo=new Login();
				User user=loginBo.Login(login, password);
				String token=createJWT(user.getUserId(),login,atualDate().getTime().getTime());
				if(token!="" && user!=null){
					user.setToken(token);
					updateUser(user);
					 return Response.ok( user.getToken()).build();
				}else{
					return Response.ok("login ou password não existe").build();
				}
		} catch (Exception e) {
			 throw new WebApplicationException(404);
		}
	}
	
	private void updateUser(User user){
		SaveUser saveUser= new SaveUser();
		saveUser.update(user);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/rentMovies")
	public Response rentMovies(@PathParam("token") String token,@PathParam("movieId") int movieId) {
		try {
			RentMovie rentMovie=new RentMovie();
			rentMovie.setDate(atualDate());
			rentMovie.setMovieId(movieId);
			Integer userId=getUserIdByToken(token); 
			if(userId!=null){
				rentMovie.setUserId(userId);
			}else{
				return Response.ok("token invalido").build();
			}
			RentMovieBO rentMovieBO= new RentMovieBO();
			boolean isRent=rentMovieBO.save(rentMovie);
			if(isRent){
				return Response.ok("Filme alugado").build();
			}
			return Response.ok("filme não tem em acervo ").build();
		}catch (Exception e) {
			 throw new WebApplicationException(404);
		}
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/giveBackMovies")
	public Response giveBackMovies(@PathParam("token")String token,@PathParam("movieId")  int movieId) {
		try {
			GiveBackMovie giveBackMovie=new GiveBackMovie();
			giveBackMovie.setDate(atualDate());
			giveBackMovie.setMovieId(movieId);
			Integer userId=getUserIdByToken(token); 
			if(userId!=null){
				giveBackMovie.setUserId(userId);
			}else{
				return Response.ok("token invalido").build();
			}
			SaveGiveBackMovie saveGiveBackMovie= new SaveGiveBackMovie();
			boolean isGiveBack=saveGiveBackMovie.save(giveBackMovie);
			if(isGiveBack){
				return Response.ok("Filme DEvolvido").build();
			}else{
				return Response.ok("Filme não DEvolvido").build();
			}
			
		}catch (Exception e) {
			 throw new WebApplicationException(404);
		}
	}
	
	private Calendar atualDate(){
		Calendar calendar = new GregorianCalendar(); 
		Date date = new Date();
		calendar.setTime(date); 
		return calendar; 

	}
	
	private Integer getUserIdByToken(String token){
		SearchUser searchUser=new SearchUser();
		User user=searchUser.searchByToken(token);
		if(user!=null){
			return user.getUserId();
		}
		return null;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/listMoviesByTitle")
	public Response listMoviesByTitle(@PathParam("token")String token,@PathParam("title")  String title){
		try {
			SearchMovies searchMovies= new SearchMovies();
			Integer userId=getUserIdByToken(token); 
			if(userId!=null){
				GenericEntity<List<Movies>> entity = new GenericEntity<List<Movies>>(searchMovies.getListMoviesByTitle(title)) {};
				return Response.status( 200 ).entity(entity).build();
			}
		}catch(Exception e){
			throw new WebApplicationException(404);
		}
		return Response.status( 404 ).build();
	}
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/listMoviesByqtd")
	public Response listMoviesByqtd(@PathParam("token")String token){
		try {
			SearchMovies searchMovies= new SearchMovies();
			Integer userId=getUserIdByToken(token); 
			if(userId!=null){
			 GenericEntity<List<Movies>> entity = new GenericEntity<List<Movies>>(searchMovies.getListMoviesAvailable()) {};
			    return Response.status( 200 ).entity(entity).build();
			}
		}catch(Exception e){
			throw new WebApplicationException(404);
		}
		 return Response.status( 404 ).build();
	}
	
	private String createJWT(int id, String subject, long ttlMillis) {
		 String jwt="";
		try {
			jwt = Jwts.builder()
					  .setSubject("users/TzMUocMF4p")
					  .setExpiration(new Date(ttlMillis))
					  .claim("id", id)
					  .claim("login", subject)
					  .signWith(
					    SignatureAlgorithm.HS256,
					    "secret".getBytes("UTF-8")
					  )
					  .compact();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 return jwt;
	}

}
