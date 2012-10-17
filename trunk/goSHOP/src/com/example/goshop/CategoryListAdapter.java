package com.example.goshop;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.data.Category;
import com.example.data.ControllerInterface;
import com.example.data.Item;
import com.example.data.ListItem;

public class CategoryListAdapter extends ArrayAdapter<ListItem>{

	private ControllerInterface data;
	private Context context;
	private ListItem[] shoppingList;
	
	public CategoryListAdapter(Context context, ControllerInterface dataController, ListItem[] values){
		super(context, R.layout.goshop_item, values);
		data = dataController;
		this.context = context;
		shoppingList = values;
	}
	
	@Override
	public View getView (int position, View convertView, ViewGroup parent){
		

		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
	    View rowView = inflater.inflate(R.layout.goshop_item, parent, false);
	    
	    TextView textView = (TextView) rowView.findViewById(R.id.item_name);
	    textView.setText(shoppingList[position].getName());
	    
	    if(shoppingList[position] instanceof Category){
	    	CheckBox checkbox = (CheckBox) rowView.findViewById(R.id.item_checkbox);
	    	Button deleteButton = (Button) rowView.findViewById(R.id.item_delete_button);
	    	
	    	checkbox.setVisibility(View.GONE);
	    	deleteButton.setVisibility(View.GONE);
	    	
	    	textView.setTextSize(32);
	    	textView.setTextColor(Color.BLUE);
	    }
	    
	    return rowView;
	}
	
	 public static ListItem[] makeTestList(){
			ArrayList<ListItem> listCache = new ArrayList<ListItem>();
			
			String[] categoryNames = {"Dairy", "Fruit", "Vegetables", "Random"};
			Item i1;
			Item i2;
			Item i3;
			
			for(String cName : categoryNames){
				Category curCat = new Category(cName);
				i1 = new Item("foo");
				i2 = new Item("bar");
				i3 = new Item("fiddly");
				listCache.add(curCat);
				listCache.add(i1);
				listCache.add(i2);
				listCache.add(i3);
			}
			ListItem[] allItems = new ListItem[listCache.size()];
			return listCache.toArray(allItems);
	    	
	    }

	
}
