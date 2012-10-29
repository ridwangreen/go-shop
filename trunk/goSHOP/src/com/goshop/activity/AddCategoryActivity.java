package com.goshop.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.goshop.R;

public class AddCategoryActivity extends Activity{
	public static String ADDED_CATEGORY_ID = "addedcategory";
	public static String CATEGORY_COLOR_ID = "categorycolor";
	
	 @Override
	 public void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);
		 setContentView(R.layout.add_category);
		 
		 Spinner spinner = (Spinner) findViewById(R.id.color_selector);
		 
		 ArrayAdapter<String> coloradapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, R.array.valid_colors){
			 @Override
			public View getDropDownView(int position, View convertView, ViewGroup parent){
				int color = Color.parseColor(this.getItem(position));
				
			 }
		 };
		 
	 }
	 @Override 
	 public void onPause() {
		 super.onPause();
	 }
	 public void addCategory(View view){
		 EditText editText = (EditText) findViewById(R.id.editText_categoryName);
		 Spinner spinner = (Spinner) findViewById(R.id.color_selector);
		 
		 String colorStr = spinner.getSelectedItem().toString();
		 
		 int color = Color.parseColor(colorStr);
		 
		 System.out.println("Edit Text: " + editText.getText());
		 Intent intent = getIntent();
		 intent.putExtra(ADDED_CATEGORY_ID, editText.getText().toString());
		 intent.putExtra(CATEGORY_COLOR_ID, color);
		 setResult(RESULT_OK, intent);
		 finish();
	 }
	 
	 
}

