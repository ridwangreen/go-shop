/**ItemManager.java
 * 
 */
package com.example.data;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import android.graphics.Color;



/**
 * This class will be responsible for managing the list of items.
 * 
 * @author Charles Lander (cjl1750@rit.edu)
 *
 */
public class ItemManager implements DataModelInterface{
	
	private final int NESTED_CAT_INDEX = 0;		// Categories will always be at index 0 in the nested data
	private final String DEFAULT_CAT_NAME = "Default";
	
	protected List<Category> orderedCategories;
	private LinkedList<ArrayList<ListItem>> nestedData;
	
	/**
	 * Builds the item manager.
	 */
	public ItemManager() {
		orderedCategories = new ArrayList<Category>();
		nestedData = new LinkedList<ArrayList<ListItem>>();
		
		buildFromXML();
		
	}
	
	/**
	 * 
	 * @param item to add
	 * @param addTo the category to add the item to
	 * @return if the item was added successfully 
	 */
	public boolean addItem(String itemName, String categoryToAdd) {
		if(itemName != null && !itemName.isEmpty() && categoryToAdd != null && !categoryToAdd.isEmpty()) {
			
			int categoryIndex = getCategoryIndex(categoryToAdd);
			
			if( categoryIndex < 0 ){ // Category was not found, add to default
				return addToDefault(itemName);
		
			}else{
				
				// Find the category list and add to it
				ArrayList<ListItem> category = nestedData.get(categoryIndex);
				return category.add(new Item(itemName));
			}
			
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
		if(categoryToRemoveFrom != null) {
			return false;
		}else {
			for(int i = 0; i < nestedData.size(); i++ ) {
				for(int j = 0; j< nestedData.get(i).size(); j++ ) {
					if(nestedData.get(i).get(j).getName().equals(itemName)) {
						nestedData.get(i).remove(j);
						return true;
					}
				}
			}
			return false;
		}
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
		
			// If the category already exists, return false
			if (getCategoryIndex(categoryName) >= 0){
				return false;
			}
			
			// Create the new Category object
			Category newCategory= new Category(categoryName, color);
			
			// Create the nested list, with the new Category as the head
			ArrayList<ListItem> newCategoryList = new ArrayList<ListItem>();
			newCategoryList.add(newCategory);
			
			// add the new nested list to the nested data
			nestedData.add(newCategoryList);
			
			// Rebuild the ordered category list
			buildOrderedCategories();
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
		buildOrderedCategories();
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
		buildOrderedCategories();
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
		makeShoppingList();
		return false;
	}

	@Override
	public List<ListItem> getShoppingList() {
		// TODO This shouldn't pass the full category, that's redundant
		ArrayList<ListItem> shoppingList = new ArrayList<ListItem>();
		
		for(ArrayList<ListItem> category : nestedData){
			shoppingList.addAll(category);
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
			System.out.println(nestedData);
			i++;
		}
	
		//ListItem[] allItems = new ListItem[listCache.size()];
		//return listCache;
    	
    }

	@Override
	public List<Category> getCategories() {
		return this.orderedCategories;
	}
	
	/**
	 * Takes the category from each 'Category' list in the nested data
	 * (the Category object is always the head element in each nested category list,
	 * so retrieval is always O(1)) and puts it into a list that reflects the index
	 * of the category list in the nested data
	 */
	private void buildOrderedCategories(){
		List<Category> categories = new ArrayList<Category>();
		
		for (ArrayList<ListItem> catList : nestedData){
			categories.add((Category) catList.get(NESTED_CAT_INDEX));
		}
		
		orderedCategories = categories;
	}
	
	public int getCategoryIndex(String categoryName){
		
		int catIndex = 0;
		for(ArrayList<ListItem> cats : nestedData){
			if(cats.get(NESTED_CAT_INDEX).getName().equals(categoryName)){
				return catIndex;
			}
			catIndex++;
		}
		
		return -1;
	}
	
	private boolean addToDefault(String itemName){
		ArrayList<ListItem> defaultList;
		boolean rebuildCategories = false;
		
		// check if the default category already exists. If not, make it
		if ( nestedData.peek().get(NESTED_CAT_INDEX).getName().equals(DEFAULT_CAT_NAME)){
			defaultList = nestedData.peek();
			
		}else{
			
			// Create the default list with head category, and add it to the
			// front of the nested data
			defaultList = new ArrayList<ListItem>();
			Category defaultCategory = new Category(DEFAULT_CAT_NAME, Color.BLACK);
			defaultList.add(defaultCategory);
			nestedData.addFirst(defaultList);
			rebuildCategories = true;
		}
		
		// Add the new item to the default list. If the default list
		// had to be created, rebuild the category list
		defaultList.add(new Item(itemName));
		if ( rebuildCategories ){
			buildOrderedCategories();
		}
		
		return true;
	}
	
	private boolean removeFromDefault(String itemName){
		return false;
	}

}
