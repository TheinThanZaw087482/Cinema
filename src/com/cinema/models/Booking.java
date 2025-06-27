package com.cinema.models;

import java.time.LocalDate;

public class Booking {
    private String name;
    private String phone;
    private String movieTitle;
    private String showTime;
    private LocalDate BookedDate;
    private String room;
    private String seatsBooked;
    private String remark;
    private String total;
    private int movieid;
    private int customerid;
    private LocalDate ShowDate;
    private String BookedStaff;
    private int showID;

    // Constructor
    public Booking(String name, String phone, String movieTitle,
    		String showTime, LocalDate BookedDate, String room, String seatsBooked,
    		String total, String remark,int movieid,int customerid,LocalDate ShowDate,String BookedStaff,int showID) {
        this.name = name;
        this.phone = phone;
        this.movieTitle = movieTitle;
        this.showTime = showTime;
        this.BookedDate = BookedDate;
        this.room = room;
        this.seatsBooked = seatsBooked;
        this.remark = remark;
        this.total =total;
        this.movieid=movieid;
        this.customerid=customerid;
        this.ShowDate = ShowDate;
        this.BookedStaff = BookedStaff;
        this.showID =showID;
    }

    public int getShowID() {
		return showID;
	}

	public void setShowID(int showID) {
		this.showID = showID;
	}

	public LocalDate getShowDate() {
		return ShowDate;
	}

	public void setShowDate(LocalDate showDate) {
		ShowDate = showDate;
	}

	public String getBookedStaff() {
		return BookedStaff;
	}

	public void setBookedStaff(String bookedStaff) {
		BookedStaff = bookedStaff;
	}

	public int getCustomerid() {
		return customerid;
	}

	public void setCustomerid(int customerid) {
		this.customerid = customerid;
	}

	public int getMovieid() {
		return movieid;
	}

	public void setMovieid(int movieid) {
		this.movieid = movieid;
	}

	// Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getShowTime() {
        return showTime;
    }

    public void setShowTime(String showTime) {
        this.showTime = showTime;
    }

    public LocalDate getBookedDate() {
        return BookedDate;
    }

    public void setBookedDate(LocalDate BookedDate) {
        this.BookedDate = BookedDate;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getSeatsBooked() {
        return seatsBooked;
    }

    public void setSeatsBooked(String seatsBooked) {
        this.seatsBooked = seatsBooked;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    // Optional: Override toString() for debugging
    @Override
    public String toString() {
        return "Booking{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", movieTitle='" + movieTitle + '\'' +
                ", showTime='" + showTime + '\'' +
                ", BookedDate='" + BookedDate + '\'' +
                ", room='" + room + '\'' +
                ", seatsBooked='" + seatsBooked + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}