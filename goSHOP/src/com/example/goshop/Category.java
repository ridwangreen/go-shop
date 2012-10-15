/**
 * 
 */
package com.example.goshop;

import java.util.HashSet;

/**
 * Data holder for Category name, color, and array of 
 * items
 * @author Ross
 *
 */
public class Category {

	private HashSet<Item> items;
	
	public Category(){
		items = new HashSet<Item>();
	}
	
	public boolean addItem(Item i){
		return items.add(i);
	}
	
	
}
