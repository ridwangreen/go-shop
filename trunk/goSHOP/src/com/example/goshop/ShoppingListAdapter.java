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
	    Button deleteButton = (Button) rowView.findViewById(R.id.item_delete_button);
    	
	    deleteButton.setTag(textView.getText());
    	/*deleteButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				System.out.println(this);
				System.out.println(v.getResources());
				System.out.println();
				
			}
    		
    	}); */
	    if(shoppingList.get(position) instanceof Category){
	    	Category curCategory = (Category) listItem;
	    	CheckBox checkbox = (CheckBox) rowView.findViewById(R.id.item_checkbox);
	    	
	    	checkbox.setVisibility(View.GONE);
	    	deleteButton.setVisibility(View.GONE);
	    	
	    	textView.setTextSize(32);
	    	textView.setTextColor(curCategory.getColor());
	    }
	    
	    return rowView;
	}
	
	/**
	 * Takes an index from the flat shopping list, and looks up the ListItem associated
	 * with it.
	 * @param index		Index in flat list
	 * @return			whether ListItem associated with index is of type Category
	 */
	public boolean isSelectedListItemCategory(int index){
		if(index > shoppingList.size() || index < 0) {
			return false;
		}else {
			ListItem selected = shoppingList.get(index);
			return (selected instanceof Category);
		}
	}
	
	public void refreshData(){
		shoppingList = data.getShoppingList();
		super.clear();
		super.addAll(shoppingList);
		super.notifyDataSetChanged();
	}

	public void clearData(){
		data.clearList();
		refreshData();
	}
	
	public boolean removeItem(String name) {
		boolean bool = data.removeItem(name, null);
		refreshData();
		return bool;
	}
	
	
	
	/**
	 * If the flatIndex 	is for an item, will return its parent's category index.
	 * If the flatIndex 	is for a Category, will return the category index.
	 * @param flatIndex 	index for the item in the flat listItem array
	 * @return				Category index
	 */
	public int getCategoryIndexFromFlatIndex(int flatIndex){
		
		ListItem listItem = shoppingList.get(flatIndex);
		
		if( listItem instanceof Category){
			
			return data.getCategoryIndex(listItem.getName());
			
		}else{
			flatIndex--;
			while( flatIndex >= 0){
				listItem = shoppingList.get(flatIndex);
				if( listItem instanceof Category){
					return data.getCategoryIndex(listItem.getName());
				}
			}
			// ERROR there was, for some reason, no parent category
			return 0;
		}
	}
	
}
