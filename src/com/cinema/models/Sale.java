package com.cinema.models;

import java.time.LocalDate;

public class Sale {
	String movieTitle;
	LocalDate date;
	String showTime;
	String room;
	String seat;
	String price;
	LocalDate SaleDate;
	int userID;
	String userName;
	String Voucher_No;
	public String getMovieTitle() {
		return movieTitle;
	}
	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public String getShowTime() {
		return showTime;
	}
	public void setShowTime(String showTime) {
		this.showTime = showTime;
	}
	public String getRoom() {
		return room;
	}
	public void setRoom(String room) {
		this.room = room;
	}
	public String getSeat() {
		return seat;
	}
	public void setSeat(String seat) {
		this.seat = seat;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public LocalDate getSaleDate() {
		return SaleDate;
	}
	public void setSaleDate(LocalDate SaleDate) {
		this.SaleDate = SaleDate;
	}
	
	public String getVoucher_No() {
		return Voucher_No;
	}
	public void setVoucher_No(String voucher_No) {
		Voucher_No = voucher_No;
	}
	public Sale(String movieTitle, LocalDate date, String showTime, String room, String seat, String price,
			LocalDate saleDate, int userID, String userName, String voucher_No) {
		super();
		this.movieTitle = movieTitle;
		this.date = date;
		this.showTime = showTime;
		this.room = room;
		this.seat = seat;
		this.price = price;
		SaleDate = saleDate;
		this.userID = userID;
		this.userName = userName;
		Voucher_No = voucher_No;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	

}
