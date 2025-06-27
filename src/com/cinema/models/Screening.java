package com.cinema.models;

import java.time.LocalDate;
import java.time.LocalTime;

public class Screening {
	private int movieID;
	private String moviename;	
	private String genre;	
	private LocalTime duration;
	private String imagepath;	
	private String description;
	private int showID;
	private LocalDate showdate;
	private int roomID;
	private String roomname;
	private String current;
	private int Revenue;
	public int getRevenue() {
		return Revenue;
	}
	public void setRevenue(int revenue) {
		Revenue = revenue;
	}
	public Screening(int movieID, String moviename, String genre, LocalTime duration, String imagepath,
			String description, int showID, LocalTime showtime, LocalDate showdate, int roomID,
			String roomname,String current,int Revenue) {
		this.movieID = movieID;
		this.moviename = moviename;
		this.genre = genre;
		this.duration = duration;
		this.imagepath = imagepath;
		this.description = description;
		this.showID = showID;
		this.showtime = showtime;
		this.showdate = showdate;
		this.roomID = roomID;
		this.roomname = roomname;
		this.current = current;
		this.Revenue=Revenue;
		
	}
	public String getCurrent() {
		return current;
	}
	public void setCurrent(String current) {
		this.current = current;
	}
	@Override
	public String toString() {
		return "Screening [movieID=" + movieID + ", moviename=" + moviename + ", genre=" + genre + ", duration="
				+ duration + ", imagepath=" + imagepath + ", description=" + description + ", showID=" + showID
				+ ", showtime=" + showtime + ", showdate=" + showdate  + ", roomID=" + roomID
				+ ", roomname=" + roomname + "]";
	}
	public int getMovieID() {
		return movieID;
	}
	public void setMovieID(int movieID) {
		this.movieID = movieID;
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
	public String getImagepath() {
		return imagepath;
	}
	public void setImagepath(String imagepath) {
		this.imagepath = imagepath;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getShowID() {
		return showID;
	}
	public void setShowID(int showID) {
		this.showID = showID;
	}
	public LocalTime getShowtime() {
		return showtime;
	}
	public void setShowtime(LocalTime showtime) {
		this.showtime = showtime;
	}
	public LocalDate getShowdate() {
		return showdate;
	}
	public void setShowdate(LocalDate showdate) {
		this.showdate = showdate;
	}
	public int getRoomID() {
		return roomID;
	}
	public void setRoomID(int roomID) {
		this.roomID = roomID;
	}
	public String getRoomname() {
		return roomname;
	}
	public void setRoomname(String roomname) {
		this.roomname = roomname;
	}
	private LocalTime showtime;	
	

}
