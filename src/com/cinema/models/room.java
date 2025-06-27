package com.cinema.models;

public class room {
	Integer roomid;
	String roomname;
	
	public room(Integer roomid,  String roomname ) {
		this.roomid=roomid;
		this.roomname=roomname;
		
	}

	@Override
	public String toString() {
		return "room [roomid=" + roomid + ", roomname=" + roomname + "]";
	}

	public Integer getRoomid() {
		return roomid;
	}

	public void setRoomid(Integer roomid) {
		this.roomid = roomid;
	}

	public String getRoomname() {
		return roomname;
	}

	public void setRoomname(String roomname) {
		this.roomname = roomname;
	}
}
