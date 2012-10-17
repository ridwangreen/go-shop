/**ItemManager.java
 * 
 */
package com.example.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.graphics.Color;



/**
 * This class will be responsible for managing the list of items.
 * 
 * @author Charles Lander (cjl1750@rit.edu)
 *
 */
public class ItemManager implements DataModelInterface{
	protected HashMap<String, Category> categories;
	protected ArrayList<Category> orderedCategories;
	
	/**
	 * Builds the item manager.
	 */
	public ItemManager() {
		categories = new HashMap<String, Category>();
		orderedCategories = new ArrayList<Category>();
	}
	
	/**
	 * 
	 * @param item to add
	 * @param addTo the category to add the item to
	 * @return if the item was added successfully 
	 */
	public boolean addItem(String itemName, String categoryToAdd) {
		if(itemName != null && !itemName.isEmpty() && categoryToAdd != null && !categoryToAdd.isEmpty()) {
			
			Category cat = categories.get(categoryToAdd);	// Find concrete category to add to
			
			if( cat == null ){		// Category does not exist
				return false;
			}
			
			return cat.addItem(new Item(itemName));
		} 
		return false;	// Either the item name or the category ID was null or empty
	}
	
	
	/**
	 * 
	 * @param item to remove
	 * @param removeFrom the category to remove the item from (may be null if unknown)
	 * @return if the item was removed successfully 
	 */
	@Override
	public boolean removeItem(String itemName, String categoryToRemoveFrom) {
		/*if(removeFrom != null) {
			return removeFrom.remove(item);
		} else {
			for(Category cat : categories) {
				if(cat.contains(item)) {				IMPLEMENT LATER
					cat.remove(item);
					return true;
				}
			}
		}*/
		return false;
	}
	
	/**
	 * 
	 * @param category to add
	 * @return if the category was added
	 */
	public boolean addCategory(String categoryName) {
		return addCategory(categoryName, Color.BLACK);
	}
	
	public boolean addCategory(String categoryName, int color) {
		
		if(categoryName != null && !categoryName.isEmpty()) {
			if (categories.containsKey(categoryName)){
				return false;
			}
			Category newCategory= new Category(categoryName, color);
			categories.put(categoryName, newCategory);
			orderedCategories.add(newCategory);
			return true;
			
		} else {
			return false;		// Category name was null or empty, or the category already exists
		}
	}
	
	/**
	 * 
	 * @param category to be removed
	 * @return if the category was removed
	 */
	public boolean removeCategory(String categoryToRemove) {
		/*if(categoryToRemove != null && !categoryToRemove.isEmpty()) {
			if( categories.containsKey(categoryToRemove) ){
				
				
				
			}else{
				return false;		IMPLEMENT LATER
			}
		} else {
			return false;
		}*/
		return false;
	}
	/**
	 * 
	 * @param item to edit
	 * @param newName the new name to give it
	 * @return if the item has been edited successfully. 
	 */
	public boolean editItem(String itemName, String newItemName) {
		/*item.editName(newName);
		if(item.getName().equals(newName)) {
			return true;
		} else {					IMPLEMENT LATER
			return false;
		}*/
		return false;
	}
	
	/**
	 * 
	 * @param cat the category to edit
	 * @param newName the new name of the category
	 * @return if the category has been edited successfully. 
	 */
	public boolean editCategory(String categoryToEdit, String newCategoryName) {
		/*cat.editName(newName);
		if(cat.equals(newName)) {
			return true;
		} else {
			return false;			IMPLEMENT LATER
		}*/
		return false;
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

	@Override
	public List<ListItem> getShoppingList() {
		// TODO This shouldn't pass the full category, that's redundant
		ArrayList<ListItem> shoppingList = new ArrayList<ListItem>();
		for(Category c : orderedCategories){
			shoppingList.add(c);
			for(Item i : c.getItems()){
				shoppingList.add(i);
			}
		}
		return shoppingList;
	}

	public void makeShoppingList(){
		//ArrayList<ListItem> listCache = new ArrayList<ListItem>();
		
		String[] categoryNames = {"Dairy", "Fruit", "Vegetables", "Random"};
		Integer[] colors = {Color.parseColor("#1515da"),  Color.parseColor("#bb0c29"), 
							Color.parseColor("#15da1a"), Color.parseColor("#6415da")};
		/*Item i1;
		Item i2;
		Item i3;*/
		
		int i = 0;
		for(String cName : categoryNames){
			/*Category curCat = new Category(cName, colors[i]);
			i1 = new Item("foo");
			i2 = new Item("bar");
			i3 = new Item("fiddly");
			listCache.add(curCat);
			listCache.add(i1);
			listCache.add(i2);
			listCache.add(i3);*/
			this.addCategory(cName, colors[i]);
			this.addItem("foo", cName);
			this.addItem("bar", cName);
			this.addItem("fiddly", cName);
			i++;
		}
		//ListItem[] allItems = new ListItem[listCache.size()];
		//return listCache;
    	
    }

	@Override
	public List<Category> getCategories() {
		return this.orderedCategories;
	}

}
