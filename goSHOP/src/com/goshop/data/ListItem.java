package com.goshop.data;

public abstract class ListItem {
	private String name;
	private boolean isCategory = false;
	
	public ListItem(String name) {
		this.name=name;
	}
	public ListItem(String name, boolean isCategory) {
		this.name=name;
		this.isCategory=isCategory;
	}
	
	public String getName() {
		return this.name;
	}

	public void editName(String newName) {
		this.name = newName;
	}
	public boolean isCategory() {
		return isCategory;
	}
	public void setCategory(boolean isCategory) {
		this.isCategory = isCategory;
	}
	
	
	
}
