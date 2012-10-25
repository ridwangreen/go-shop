package com.example.goshop;

import java.util.List;
import java.util.Random;

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
import com.example.data.DataModelInterface;
import com.example.data.ItemManager;
import com.example.data.ListItem;

public class ShoppingListAdapter extends ArrayAdapter<ListItem>{

	private DataModelInterface data;
	private Context context;
	private List<ListItem> shoppingList;
	
	// Used for testing
	private static final Random randomGen = new Random();		
	private static final Integer[] colors = {Color.parseColor("#1515da"),  Color.parseColor("#bb0c29"), 
			Color.parseColor("#15da1a"), Color.parseColor("#6415da")};
	
	public ShoppingListAdapter(Context context, DataModelInterface data){
		super(context, R.layout.goshop_item);
		this.data = data;
		this.context = context;
		shoppingList = data.getShoppingList();
		super.addAll(shoppingList);
	}
	
	public static int getRandomColor(){
		int rando = randomGen.nextInt(colors.length + 1);
		return colors[rando];
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
	
	public void addItem(String newItem, String curCategory){
		data.addItem(newItem, curCategory);
		refreshData();
	}
	
	public boolean addCategory(String categoryName, int color){
		boolean bool = data.addCategory(categoryName, color);
		refreshData();
		return bool;
	}
	
	public boolean isSelectedListItemCategory(int index){
		if(index > shoppingList.size() || index < 0) {
			return false;
		}else {
			ListItem selected = shoppingList.get(index);
			return (selected instanceof Category);
		}
	}
	
	private void refreshData(){
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
	
	public boolean removeCategory(String name) {
		for(int i =0; i < data.getNestedData().size(); i++) {
			if(data.getNestedData().get(i).get(0).getName().equals(name)) {
				data.getNestedData().remove(i);
				
				refreshData();
				return true;
			}
		}
		refreshData();
		return false;
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
	
	public void save(Context ctx){
		data.save(ctx);
	}
	
	//TODO Delete after testing
	public void load(Context ctx) {
		((ItemManager) data).buildFromXML(ctx);
	}
}
