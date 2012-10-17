package com.example.goshop;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.data.Category;
import com.example.data.DataModelInterface;
import com.example.data.ListItem;

public class ShoppingListAdapter extends ArrayAdapter<ListItem>{

	private DataModelInterface data;
	private Context context;
	private List<ListItem> shoppingList;
	
	public ShoppingListAdapter(Context context, DataModelInterface data){
		super(context, R.layout.goshop_item);
		this.data = data;
		this.context = context;
		shoppingList = data.getShoppingList();
		super.addAll(shoppingList);
	}
	
	@Override
	public View getView (int position, View convertView, ViewGroup parent){
		

		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
	    View rowView = inflater.inflate(R.layout.goshop_item, parent, false);
	    
	    ListItem listItem = shoppingList.get(position);
	    
	    TextView textView = (TextView) rowView.findViewById(R.id.item_name);
	    textView.setText(listItem.getName());
	    
	    if(shoppingList.get(position) instanceof Category){
	    	Category curCategory = (Category) listItem;
	    	CheckBox checkbox = (CheckBox) rowView.findViewById(R.id.item_checkbox);
	    	Button deleteButton = (Button) rowView.findViewById(R.id.item_delete_button);
	    	
	    	checkbox.setVisibility(View.GONE);
	    	deleteButton.setVisibility(View.GONE);
	    	
	    	textView.setTextSize(32);
	    	textView.setTextColor(curCategory.getColor());
	    }
	    
	    return rowView;
	}
	
	public void addItem(String newItem, String curCategory){
		
		data.addItem(newItem, curCategory);
		refreshData();
	}
	
	public boolean addCategory(String categoryName, int color){
		return data.addCategory(categoryName, color);
	}
	
	
	private void refreshData(){
		shoppingList = data.getShoppingList();
		super.clear();
		super.addAll(shoppingList);
		super.notifyDataSetChanged();
	}

	
}
