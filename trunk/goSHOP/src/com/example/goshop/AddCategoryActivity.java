package com.example.goshop;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddCategoryActivity extends Activity{
	public static String ADDED_CATEGORY_ID = "addedcategory";
	 @Override
	 public void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);
		 setContentView(R.layout.add_category);
	 }
	 @Override 
	 public void onPause() {
		 super.onPause();
	 }
	 public void addCategory(View view){
		 EditText editText = (EditText) findViewById(R.id.editText1);
		 System.out.println("Edit Text: " + editText.getText());
		 Intent intent = getIntent();
		 intent.putExtra(ADDED_CATEGORY_ID, editText.getText().toString());
		 setResult(RESULT_OK, intent);
		 finish();
	 }
}

