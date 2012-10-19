/**
 * 
 */
package com.example.data;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


/**
 * This is the interface that the controller for Go SHOP must adhere too.
 * 
 * @author Charles Lander (cjl1750@rit.edu)
 *
 */
public interface DataModelInterface {
	
	/**
	 * 
	 * @param toAdd the category to add
	 * @return if the category was added
	 */
	public boolean addCategory(String categoryName);
	
	/**
	 * 
	 * @param categoryName
	 * @param color
	 * @return
	 */
	public boolean addCategory(String categoryName, int color);
	
	/**
	 * 
	 * @param toRemove the category to remove
	 * @return if the category was removed
	 */
	public boolean removeCategory(String categoryToRemove);
	
	/**
	 * 
	 * @param toEdit the category to edit
	 * @param newName the new name of the category
	 * @return if the category was edited
	 */
	public boolean editCategory(String categoryToEdit, String newCategoryName);
	
	/**
	 * 
	 * @param item to add
	 * @param toAdd the category to add it to
	 * @return if it was added
	 */
	public boolean addItem(String itemName, String categoryToAdd);
	
	/**
	 * 
	 * @param item to remove
	 * @param toRemove the category to remove it from (may be null)
	 * @return if it was removed
	 */
	public boolean removeItem(String itemName, String categoryToRemoveFrom);
	
	/**
	 * 
	 * @param item to edit
	 * @param newName the new name of the item
	 * @return  if the item was edited
	 */
	public boolean editItem(String itemName, String newItemName);
	
	/**
	 * 
	 * @return An ordered list of a category followed by its items, followed
	 * 		   by the next category, etc.
	 */
	public List<ListItem> getShoppingList();
	
	public List<Category> getCategories();
	
	public int getCategoryIndex(String categoryName);
	
	public LinkedList<ArrayList<ListItem>> getNestedData();
}
