/**
 * 
 */
package com.goshop.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.goshop.R;

/**
 * @author Ross
 *
 */
public class ColorListAdapter extends ArrayAdapter<String>{

	private Context context;
	
	public ColorListAdapter(Context context){
		super(context, android.R.layout.simple_spinner_item);
		
		Resources res = context.getResources();
		String[] colors = res.getStringArray(R.array.valid_colors);
		
		addAll(colors);
	}
	
	@Override
	public View getView (int position, View convertView, ViewGroup parent){
		System.out.println("GET VIEW " + context);
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	    View rowView = inflater.inflate(android.R.layout.simple_spinner_item, parent, false);
	    /*System.out.println("Dropdown found");
	    //TextView textView = (TextView) rowView.findViewById(android.R.id.text1);
	    
		int color = Color.parseColor(getItem(position));
		//textView.setText("");
		 
		rowView.setBackgroundColor(color);*/
	    
	    return rowView;
	}
	
	@Override
	public View getDropDownView(int position, View convertView, ViewGroup parent){
		System.out.println("DROPDOWN*******************************");
		return getView(position, convertView, parent);
	}
}
