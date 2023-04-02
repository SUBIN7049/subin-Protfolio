package com.web.jdbc;

public class reservation_service {
	private String reservation_num;
	private String service_name;
	
	public reservation_service(String reservation_num, String service_name) {
		super();
		this.reservation_num = reservation_num;
		this.service_name = service_name;
	}

	public String getReservation_num() {
		return reservation_num;
	}

	public void setReservation_num(String reservation_num) {
		this.reservation_num = reservation_num;
	}

	public String getService_name() {
		return service_name;
	}

	public void setService_name(String service_name) {
		this.service_name = service_name;
	}

	@Override
	public String toString() {
		return "reservation_service [reservation_num=" + reservation_num + ", service_name=" + service_name + "]";
	}
	
	
}
