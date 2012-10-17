package com.example.goshop;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class CategoryListAdapter extends ArrayAdapter<Category>{

	private ControllerInterface data;
	
	public CategoryListAdapter(Context context, int resourceID, ControllerInterface dataController){
		super(context, resourceID);
		data = dataController;
	}
	
	@Override
	public View getView (int position, View convertView, ViewGroup parent){
		/*List<ListItem> shoppingList = data.getShoppingList();
		
		ListItem curListItem = shoppingList.get(position);
		
		if(curListItem instanceof Category){
			Category curCategory = (Category) curListItem;
			
			
		}else if(curListItem instanceof Item){
			Item curItem = (Item) curListItem;
			
			
		}else{
			//TODO: Make this throw an exception
			return null;
		}*/
		
		return null;
	}

	
}
