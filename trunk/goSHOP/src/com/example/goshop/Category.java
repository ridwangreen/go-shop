/**
 * 
 */
package com.example.goshop;

import java.util.ArrayList;

/**
 * Data holder for Category name, color, and array of 
 * items
 * @author Ross
 *
 */
public class Category {

	private ArrayList<Item> items;
	private String name;
	
	public Category(String name){
		items = new ArrayList<Item>();
		this.name = name;
	}
	
	/**
	 * 
	 * @param i the item to add
	 * @return if the item was added successfully
	 */
	public boolean addItem(Item i){
		return items.add(i);
	}
	
	/**
	 * 
	 * @param i the item
	 * @return if the item is inside the category
	 */
	public boolean contains(Item i ) {
		return items.contains(i);
	}
	
	/**
	 * 
	 * @param i the item to remove
	 * @return if the item was removed successfully
	 */
	public boolean remove(Item i ) {
		return items.remove(i);
	}
	
	/**
	 * 
	 * @param newName the new name of the category
	 */
	public void editName(String newName) {
		this.name = newName;
	}
	
	public String getName() {
		return this.name;
	}
	
	public ArrayList<Item> getItems(){
		return items;
	}
	
}
