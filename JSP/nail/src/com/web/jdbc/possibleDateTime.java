package com.web.jdbc;

import java.time.LocalDate;
import java.time.LocalTime;

public class possibleDateTime {
	private int date_num;
	private LocalDate possible_date;
	private LocalTime possible_time;
	private int reservation_ox;
	
	public possibleDateTime(int date_num, LocalDate possible_date, LocalTime possible_time, int reservation_ox) {
		super();
		this.date_num = date_num;
		this.possible_date = possible_date;
		this.possible_time = possible_time;
		this.reservation_ox = reservation_ox;
	}

	public int getDate_num() {
		return date_num;
	}

	public void setDate_num(int date_num) {
		this.date_num = date_num;
	}

	public LocalDate getPossible_date() {
		return possible_date;
	}

	public void setPossible_date(LocalDate possible_date) {
		this.possible_date = possible_date;
	}

	public LocalTime getPossible_time() {
		return possible_time;
	}

	public void setPossible_time(LocalTime possible_time) {
		this.possible_time = possible_time;
	}

	public int getReservation_ox() {
		return reservation_ox;
	}

	public void setReservation_ox(int reservation_ox) {
		this.reservation_ox = reservation_ox;
	}

	@Override
	public String toString() {
		return "possibleDateTime [date_num=" + date_num + ", possible_date=" + possible_date + ", possible_time="
				+ possible_time + ", reservation_ox=" + reservation_ox + "]";
	}
	
	
}
