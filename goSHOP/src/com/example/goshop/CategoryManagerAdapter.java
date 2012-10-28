/**
 * 
 */
package com.example.goshop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.data.Category;
import com.example.data.DataModelInterface;

/**
 * @author Ross
 *
 */
public class CategoryManagerAdapter extends CategoryListAdapter {
	
	public CategoryManagerAdapter(Context context, DataModelInterface data) {
		super(context, data);
	}
	
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent){
	    
	    LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
	    View rowView = inflater.inflate(R.layout.goshop_item, parent, false);
	    
	    Category curCategory = categories.get(position);
	    
	    TextView textView = (TextView) rowView.findViewById(R.id.item_name);
	    textView.setText(curCategory.getName());
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
    	CheckBox checkbox = (CheckBox) rowView.findViewById(R.id.item_checkbox);
    	
    	checkbox.setVisibility(View.GONE);
    	
    	textView.setTextSize(32);
    	textView.setTextColor(curCategory.getColor());
	    
	    return rowView;
	}

}