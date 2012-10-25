package com.example.goshop;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;

public class RemoveCategoryActivity extends Activity {
	public static String REMOVE_CATEGORY_ID = "removedCategory";
	private CategoryListAdapter categoryListAdapter;
	 @Override
	 public void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);
		 setContentView(R.layout.remove_category);		 
		 
		 Spinner categoryList = (Spinner) findViewById(R.id.category_list);
		 
		 /*categoryListAdapter = new CategoryListAdapter(this, GoShopActivity.data);
		 categoryList.setAdapter(categoryListAdapter);*/
	 }
	 @Override 
	 public void onPause() {
		 super.onPause();
	 }
	 public void removeCategory(View view){
		 System.out.println("Removing category");
		 Spinner spinner = (Spinner) findViewById(R.id.category_list);
		 System.out.println(spinner.getSelectedItem());
		 Intent intent = getIntent();
		 if(spinner.getSelectedItem() == null) {
			 setResult(RESULT_CANCELED);
			 finish();
		 } else {
			 intent.putExtra(REMOVE_CATEGORY_ID, spinner.getSelectedItem().toString());
			 setResult(RESULT_OK, intent);
			 finish();
		 }
	 }
}
