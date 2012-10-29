/**ItemManager.java
 * 
 */
package com.example.data;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import android.content.Context;
import android.graphics.Color;



/**
 * This class will be responsible for managing the list of items.
 * 
 * @author Charles Lander (cjl1750@rit.edu)
 *
 */
public class ItemManager implements DataModelInterface{
	
	private static ItemManager self;
	
	private final int NESTED_CAT_INDEX = 0;				// Categories will always be at index 0 in the nested data
	private final String DEFAULT_CAT_NAME = "Default";
	
	protected List<Category> orderedCategories;
	private LinkedList<ArrayList<ListItem>> nestedData;
	
	/**
	 * Builds the item manager.
	 */
	private ItemManager() {
		orderedCategories = new ArrayList<Category>();
		nestedData = new LinkedList<ArrayList<ListItem>>();
		
		addCategory("Default");
		
		buildFromXML();
		
	}
	
	public static ItemManager getItemManager(){
		if(self == null){
			self = new ItemManager();
		}
		return self;
	}
	
	/**
	 * 
	 * @param item to add
	 * @param addTo the category to add the item to
	 * @return if the item was added successfully 
	 */
	public boolean addItem(String itemName, int categoryIndex) {
		if(itemName != null && !itemName.isEmpty() && categoryIndex >= 0) {
			
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
	 * @param category to add
	 * @return if the category was added
	 */
	public boolean addCategory(String categoryName) {
		return addCategory(categoryName, Color.BLACK);
	}
	
	public boolean addCategory(String categoryName, int color) {
		
		if(categoryName != null && !categoryName.isEmpty()) {
		
			// If the category already exists, return false
			for( Category c : orderedCategories){
				if( c.getName().equals(categoryName)){
					return false;
				}
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
	public boolean removeCategory(int categoryIndex) {
		
		nestedData.remove(categoryIndex);
		buildOrderedCategories();
		
		return true;
	}
	/**
	 * 
	 * @param item to edit
	 * @param newName the new name to give it
	 * @return if the item has been edited successfully. 
	 */
	public boolean editItem(int itemFlatPosition, int categoryIndex, String newItemName) {
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
	public boolean editCategory(int categoryToEdit, String newCategoryName, int newColor) {
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
	private boolean buildFromXML() {
		makeShoppingList();	//TODO If we need test cases uncomment this
		return false;
	}

	@Override
	public List<ListItem> getShoppingList() {

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
		
		int i = 0;
		for(String cName : categoryNames){
			this.addCategory(cName, colors[i]);
			this.addItem("foo", i+1);
			this.addItem("bar", i+1);
			this.addItem("fiddly", i+1);
			System.out.println(nestedData);
			i++;
		}
    	
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
	
	/**
	 * This will clear the list of ALL user-entered items and ALL user-entered categories.
	 * The default category will be rebuilt. This should only be a development debugging tool.
	 */
	public void deleteData() {
		// TODO Auto-generated method stub
		orderedCategories = new ArrayList<Category>();
		nestedData = new LinkedList<ArrayList<ListItem>>();
		
		addCategory("Default");
		// Write to xml?
	}

	@Override
	public boolean removeItem(int positionInShoppingList, int categoryIndex) {
		ArrayList<ListItem> items = nestedData.get(categoryIndex);
		items.remove(flatIndexToNestedIndex(positionInShoppingList, categoryIndex));
		return true;
	}


	@Override
	public void deleteCheckedItems() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Returns an index of the item WITHIN ITS CATEGORY. The category index
	 * needs to be found for a full nested address.
	 * @param flatIndex
	 * @return
	 */
	private int flatIndexToNestedIndex(int flatIndex, int categoryIndex){
		
		int itemCounter = 0;
		int catPosition = 0;
		for( ArrayList<ListItem> category : nestedData){
			
			if( catPosition == categoryIndex){
				break;
			}
			
			itemCounter += category.size();
		}
		
		return flatIndex - itemCounter;
	}
	
}
