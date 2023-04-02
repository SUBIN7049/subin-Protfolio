package com.web.jdbc;

public class nail_menu {
	private String menu;
	private String price;
	private int category;
	
	public nail_menu(String menu, String price, int category) {
		super();
		this.menu = menu;
		this.price = price;
		this.category = category;
	}
	public nail_menu(String menu, String price) {
		super();
		this.menu = menu;
		this.price = price;
		
	}
	

	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}
	
	
}
