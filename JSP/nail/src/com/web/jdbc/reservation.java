package com.web.jdbc;

import java.time.LocalDateTime;

public class reservation {
	private String user_id;
	private int reservation_number;
	private int reservation_datenum;
	
	public reservation(String user_id, int reservation_number, int reservation_datenum) {
		super();
		this.user_id = user_id;
		this.reservation_number = reservation_number;
		this.reservation_datenum = reservation_datenum;
	}
	
	@Override
	public String toString() {
		return "reservation [user_id=" + user_id + ", reservation_number=" + reservation_number
				+ ", reservation_datenum=" + reservation_datenum + "]";
	}

	public reservation(String user_id) {
		super();
		this.user_id = user_id;
		
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public int getReservation_number() {
		return reservation_number;
	}

	public void setReservation_number(int reservation_number) {
		this.reservation_number = reservation_number;
	}

	public int getReservation_datenum() {
		return reservation_datenum;
	}

	public void setReservation_datenum(int reservation_datenum) {
		this.reservation_datenum = reservation_datenum;
	}
	
	
	
}