/**
 * 
 */
package com.example.goshop;

/**
 * Data holder for each individual shopping list item.
 * Contains information on the item name
 * @author Ross
 *
 */
public class Item extends ListItem{

	private String itemName;
	
	public Item(String name){
		itemName = name;
	}
	
	public void editName(String newName){
		itemName = newName;
	}
	
	public String getName(){
		return itemName;
	}
	
	@Override
	public boolean equals(Object o){
		if (o instanceof Item){
			Item mystery = (Item) o;
			return mystery.getName().equals(itemName);
		}else{
			return false;
		}
	}
}
