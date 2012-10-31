/**
 * 
 */
package com.goshop.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.goshop.data.Category;
import com.goshop.data.DataModelInterface;
import com.goshop.data.ListItem;

/**
 * @author Ross
 *
 */
public class CategoryListAdapter extends ArrayAdapter<ListItem>{

	protected DataModelInterface data;
	protected Context context;
	protected List<Category> categories;
	
	public CategoryListAdapter(Context context, DataModelInterface data) {
		super(context, android.R.layout.simple_spinner_dropdown_item);
		this.data = data;
		this.context = context;
		refreshData();
	}
	
	@Override
	public View getView (int position, View convertView, ViewGroup parent){

		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
	    View rowView = inflater.inflate(android.R.layout.simple_spinner_dropdown_item, parent, false);
	    
	    TextView textView = (TextView) rowView.findViewById(android.R.id.text1);
	    
	    Category curCategory = categories.get(position);
	    textView.setText(curCategory.getName());
	    textView.setTextColor(curCategory.getColor());
	    textView.setTextSize(36);
	    
	    return rowView;
	}
	
	@Override
	public View getDropDownView(int position, View convertView, ViewGroup parent){
		return getView(position, convertView, parent);
	}
	
	public void refreshData(){
		categories = data.getCategories();
		
		super.clear();
		super.addAll(categories);
		super.notifyDataSetChanged();
	}
	
	public void removeCategory(String name) {
		for(int i = 0; i < categories.size(); i++ ) {
			if(categories.get(i).getName().equals(name)) {
				Category cat = categories.remove(i);
				this.remove(cat);
				return;
			}
		}
	}
	
	public int findCategoryIndexFromName(String categoryName){
		
		for( int i=0; i<categories.size(); i++){
			if( categoryName.equals(categories.get(i).getName())){
				return i;
			}
		}
		return -1;
	}

}
