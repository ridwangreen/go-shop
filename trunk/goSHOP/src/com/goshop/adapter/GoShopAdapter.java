/**
 * 
 */
package com.goshop.adapter;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.example.data.DataModelInterface;
import com.example.data.ItemManager;
import com.example.data.ListItem;

/**
 * @author Ross
 *
 */
public class GoShopAdapter{

	private Context context;
	
	private DataModelInterface data; 
	
	private ShoppingListAdapter shoppingList;
	private CategoryListAdapter categoryList;
	private CategoryManagerAdapter categoryManagerList;
	
	public GoShopAdapter(Context context) {	
		this.context = context;
		
		data = ItemManager.getItemManager();
		
		shoppingList = new ShoppingListAdapter(context, data);
		categoryList = new CategoryListAdapter(context, data);
		categoryManagerList = new CategoryManagerAdapter(context, data);
	}
	
	public ArrayAdapter<ListItem> getShoppingAdapter(){
		return shoppingList;
	}
	
	public ArrayAdapter<ListItem> getCategoryAdapter(){
		return categoryList;
	}
	
	public ArrayAdapter<ListItem> getCategoryManagerAdapter(){
		return categoryManagerList;
	}
	
	public boolean addItem(String itemName, String categoryName){
		boolean success = data.addItem(itemName, categoryName);
		refreshAdapterData(false);
		return success;
	}
	
	public boolean removeItem(int flatIndex){
		
		return false;
	}

	public boolean addCategory(String categoryName, int color){
		boolean success = data.addCategory(categoryName, color);
		refreshAdapterData();
		return success;
	}
	
	public int getCategoryIndexFromFlatIndex(int flatIndex){
		return shoppingList.getCategoryIndexFromFlatIndex(flatIndex);
	}
	
	public String getCategoryNameFromFlatIndex(int flatIndex){
		int catPos = getCategoryIndexFromFlatIndex(flatIndex);
		
		return "";
	}
	
	/**
	 * 
	 * @param refreshCategories		Will refresh the category list if true. Optional because
	 * 								item addition/removal is irrelevant to categories
	 */
	private void refreshAdapterData(boolean refreshCategories){
		shoppingList.refreshData();
		if( refreshCategories ){
			categoryList.refreshData();
		}
	}
	
	public boolean isSelectedListItemCategory(int index){
		return shoppingList.isSelectedListItemCategory(index);
	}
	
	private void refreshAdapterData(){
		refreshAdapterData(true);
	}
	
	public void clearData(){
		data.clearList();
		refreshAdapterData();
	}
	
	public boolean removeCategory(int categoryPosition) {
		
		// Find the index of the category in the nested data and just
		// delete it from the nested data. Then refresh data
		
		boolean result = data.removeCategory(categoryPosition);
		
		if( result ){
			refreshAdapterData();
		}

		return result;
	}
	
}
