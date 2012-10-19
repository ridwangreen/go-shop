/**
 * 
 */
package com.example.goshop;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.data.Category;
import com.example.data.DataModelInterface;

/**
 * @author Ross
 *
 */
public class CategoryListAdapter extends ArrayAdapter<Category>{

	private Context context;
	private List<Category> categories;
	
	public CategoryListAdapter(Context context, DataModelInterface data) {
		super(context, android.R.layout.simple_spinner_dropdown_item);
		// TODO Auto-generated constructor stub
		this.context = context;
		categories = data.getCategories();
		
		super.addAll(categories);
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
	public void addCategory(Category category) {
		this.add(category);
		this.categories.add(category);
	}
	
	public void removeCategory(Category category) {
		this.remove(category);
		this.categories.remove(category);
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

}
