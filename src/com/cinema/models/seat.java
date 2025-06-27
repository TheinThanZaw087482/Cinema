package com.cinema.models;
public class seat {
	String seatid;
	String type;
	int price;
	@Override
	public String toString() {
		return "seat [seatid=" + seatid + ", type=" + type + ", price=" + price + "]";
	}
	
	
	public seat(String seatid,  String type,Integer price ) {
		this.seatid=seatid;
		this.type=type;
		this.price=price;
		
	}
	public String getSeatid() {
		return seatid;
	}
	public void setSeatid(String seatid) {
		this.seatid = seatid;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
}
