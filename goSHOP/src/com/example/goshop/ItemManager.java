/**ItemManager.java
 * 
 */
package com.example.goshop;

import java.util.ArrayList;


/**
 * This class will be responsible for managing the list of items.
 * 
 * @author Charles Lander (cjl1750@rit.edu)
 *
 */
public class ItemManager implements ControllerInterface{
	protected ArrayList<Category> categories;
	
	/**
	 * Builds the item manager.
	 */
	public ItemManager() {
		categories = new ArrayList<Category>();
	}
	
	/**
	 * 
	 * @param item to add
	 * @param addTo the category to add the item to
	 * @return if the item was added successfully 
	 */
	public boolean addItem(Item item, Category addTo) {
		if(addTo != null) {
			return addTo.addItem(item);
		} 
		return false;
	}
	
	/**
	 * 
	 * @param item to remove
	 * @param removeFrom the category to remove the item from (may be null if unknown)
	 * @return if the item was removed successfully 
	 */
	public boolean removeItem(Item item, Category removeFrom) {
		if(removeFrom != null) {
			return removeFrom.remove(item);
		} else {
			for(Category cat : categories) {
				if(cat.contains(item)) {
					cat.remove(item);
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * 
	 * @param category to add
	 * @return if the category was added
	 */
	public boolean addCategory(Category category) {
		if(category != null) {
			return categories.add(category);
		} else {
			return false;
		}
	}
	
	/**
	 * 
	 * @param category to be removed
	 * @return if the category was removed
	 */
	public boolean removeCategory(Category category) {
		if(category != null) {
			return categories.remove(category);
		} else {
			return false;
		}
	}
	/**
	 * 
	 * @param item to edit
	 * @param newName the new name to give it
	 * @return if the item has been edited successfully. 
	 */
	public boolean editItem(Item item, String newName) {
		item.editName(newName);
		if(item.getName().equals(newName)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 
	 * @param cat the category to edit
	 * @param newName the new name of the category
	 * @return if the category has been edited successfully. 
	 */
	public boolean editCategory(Category cat, String newName) {
		cat.editName(newName);
		if(cat.equals(newName)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Writes the current list to the xml file
	 * @return if the file wrote successfully
	 */
	public boolean writeToXML(){
		return false;
	}
	
	/**
	 * Builds the list from the xml file
	 * @return if the list was built successfully
	 */
	public boolean buildFromXML() {
		return false;
	}

	

}
