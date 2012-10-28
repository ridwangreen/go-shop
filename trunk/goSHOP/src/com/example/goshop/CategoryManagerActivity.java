/**
 * 
 */
package com.example.goshop;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

/**
 * @author Ross
 *
 */
public class CategoryManagerActivity extends Activity{

	GoShopAdapter adapter;
	
	 public void onCreate(Bundle savedInstanceState) {
		   super.onCreate(savedInstanceState);
		   setContentView(R.layout.category_manager);
		   
		   adapter = new GoShopAdapter(this);
		   
	       ListView categoryList = (ListView) findViewById(R.id.listview_categoryManager);
	              
	       categoryList.setAdapter(adapter.getCategoryManagerAdapter());
	 }
	
	 
	 public void removeQuickItem(View view){
		 System.out.println("OMG YOU REMOVED ONE");
	 }
	 
}
