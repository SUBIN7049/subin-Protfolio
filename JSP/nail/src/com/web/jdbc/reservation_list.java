package com.web.jdbc;

public class reservation_list {
	private int date_num;
	private String user_id;
	private String possible_date;
	private String possible_time;
	private String service_name;
	
	public reservation_list(String user_id, String possible_date, String possible_time, String service_name) {
		super();
		this.user_id = user_id;
		this.possible_date = possible_date;
		this.possible_time = possible_time;
		this.service_name = service_name;
	}
	
	
	public reservation_list(int date_num, String possible_date, String possible_time) {
		super();
		this.date_num = date_num;
		this.possible_date = possible_date;
		this.possible_time = possible_time;
	}

	

	

	public int getDate_num() {
		return date_num;
	}

	public void setDate_num(int date_num) {
		this.date_num = date_num;
	}
	
	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getPossible_date() {
		return possible_date;
	}

	public void setPossible_date(String possible_date) {
		this.possible_date = possible_date;
	}

	public String getPossible_time() {
		return possible_time;
	}

	public void setPossible_time(String possible_time) {
		this.possible_time = possible_time;
	}

	public String getService_name() {
		return service_name;
	}

	public void setService_name(String service_name) {
		this.service_name = service_name;
	}
	
	
}
