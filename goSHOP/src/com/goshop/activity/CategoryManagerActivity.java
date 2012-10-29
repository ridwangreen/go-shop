/**
 * 
 */
package com.goshop.activity;

import com.example.goshop.R;
import com.example.goshop.R.id;
import com.example.goshop.R.layout;
import com.goshop.adapter.GoShopAdapter;

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
		 ListView catManagerList = (ListView) findViewById(R.id.listview_categoryManager);
		 
		 int categoryPosition = catManagerList.getPositionForView(view);
		 
		 adapter.removeCategory(categoryPosition);
	 }
	 
}
