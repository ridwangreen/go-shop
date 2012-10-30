package com.goshop.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.goshop.R;
import com.goshop.adapter.ColorListAdapter;
import com.goshop.adapter.GoShopAdapter;

public class AddCategoryActivity extends Activity{
	public static String ADDED_CATEGORY_ID = "addedcategory";
	public static String CATEGORY_COLOR_ID = "categorycolor";
	
	private ColorListAdapter adapter;
	
	 @Override
	 public void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);
		 setContentView(R.layout.add_category);
		 
		 adapter = new ColorListAdapter(this);
		 
		 Spinner spinner = (Spinner) findViewById(R.id.color_selector);
		 
		 spinner.setAdapter(adapter);
		 
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

