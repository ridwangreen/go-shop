package com.example.goshop;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class CategoryListAdapter extends ArrayAdapter<Category>{

	
	public CategoryListAdapter(Context context, int resourceID, ControllerInterface dataController){
		super(context, resourceID);
		
	}
	
	@Override
	public View getView (int position, View convertView, ViewGroup parent){
		
	}

	
}
