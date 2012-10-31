/**
 * 
 */
package com.goshop.data;


/**
 * Data holder for each individual shopping list item.
 * Contains information on the item name
 * @author Ross
 *
 */
public class Item extends ListItem{

	
	public Item(String name){
		super(name);
	}
	
	@Override
	public boolean equals(Object o){
		if (o instanceof Item){
			Item mystery = (Item) o;
			return mystery.getName().equals(getName());
		}else{
			return false;
		}
	}
	
	public String toString(){
		return getName();
	}
}
