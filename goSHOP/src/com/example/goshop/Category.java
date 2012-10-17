/**
 * 
 */
package com.example.goshop;

import java.util.ArrayList;

import android.graphics.Color;

/**
 * Data holder for Category name, color, and array of 
 * items
 * @author Ross
 *
 */
public class Category extends ListItem{

	private ArrayList<Item> items;
	private int color;
	
	public Category(String name, int color){
		super(name);
		items = new ArrayList<Item>();
		this.color = color;
	}
	
	public Category(String name){
		super(name);
		items = new ArrayList<Item>();
		this.color = Color.BLACK;
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
	
	public ArrayList<Item> getItems(){
		return items;
	}
	
}
