package com.cinema.models;

import java.time.LocalTime;

public class MovieData {
	private int id;
	private String moviename;
	private String genre;
	private LocalTime duration;
	private String image;
	private String description;
	private String current;


	@Override
	public String toString() {
		return  moviename ;
	}
	
	

	public String getCurrent() {
		return current;
	}



	public void setCurrent(String current) {
		this.current = current;
	}



	public MovieData(int id, String moviename, String genre, LocalTime duration, String image, String description, String current) {
		this.id = id;
		this.moviename = moviename;
		this.genre = genre;
		this.duration = duration;
		this.image = image;
		this.description = description;
		this.current = current;
	}



	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getMoviename() {
		return moviename;
	}


	public void setMoviename(String moviename) {
		this.moviename = moviename;
	}


	public String getGenre() {
		return genre;
	}


	public void setGenre(String genre) {
		this.genre = genre;
	}


	public LocalTime getDuration() {
		return duration;
	}


	public void setDuration(LocalTime duration) {
		this.duration = duration;
	}


	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}
	

	
	
}
