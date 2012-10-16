/**
 * 
 */
package com.example.goshop;

/**
 * This is the interface that the controller for Go SHOP must adhere too.
 * 
 * @author Charles Lander (cjl1750@rit.edu)
 *
 */
public interface ControllerInterface {
	
	/**
	 * 
	 * @param toAdd the category to add
	 * @return if the category was added
	 */
	public boolean addCategory(Category toAdd);
	
	/**
	 * 
	 * @param toRemove the category to remove
	 * @return if the category was removed
	 */
	public boolean removeCategory(Category toRemove);
	
	/**
	 * 
	 * @param toEdit the category to edit
	 * @param newName the new name of the category
	 * @return if the category was edited
	 */
	public boolean editCategory(Category toEdit, String newName);
	
	/**
	 * 
	 * @param item to add
	 * @param toAdd the category to add it to
	 * @return if it was added
	 */
	public boolean addItem(Item item, Category toAdd);
	
	/**
	 * 
	 * @param item to remove
	 * @param toRemove the category to remove it from (may be null)
	 * @return if it was removed
	 */
	public boolean removeItem(Item item, Category toRemove);
	
	/**
	 * 
	 * @param item to edit
	 * @param newName the new name of the item
	 * @return  if the item was edited
	 */
	public boolean editItem(Item item, String newName);
	
	/**
	 * 
	 * @return An ordered list of a category followed by its items, followed
	 * 		   by the next category, etc.
	 */
	public List<ListItem> getShoppingList();
	
}
