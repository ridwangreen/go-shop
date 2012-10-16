package com.example.goshop;

public abstract class ListItem {
	private String name;
	
	public ListItem(String name) {
		this.name=name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void editName(String newName) {
		this.name = newName;
	}
}
