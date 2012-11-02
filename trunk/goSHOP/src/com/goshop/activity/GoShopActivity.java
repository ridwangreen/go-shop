package com.goshop.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.goshop.R;
import com.goshop.adapter.GoShopAdapter;

public class GoShopActivity extends Activity {
	private static int ADD_CATEGORY_REQUEST_CODE = 12354;
	private static int REMOVE_CATEGORY_REQUEST_CODE = 123456789;
	public static String DATA_MODEL = "datamodeler";

	private GoShopAdapter adapter;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
	   super.onCreate(savedInstanceState);
	   setContentView(R.layout.activity_go_shop);
	   
       ListView shoppingList = (ListView) findViewById(R.id.shopping_list_view);
       Spinner categoryList = (Spinner) findViewById(R.id.category_list);
              
       adapter = new GoShopAdapter(this);
       
       // For changing the current category with a click
       shoppingList.setOnItemClickListener(new OnItemClickListener() {
    	      public void onItemClick(AdapterView<?> myAdapter, View myView, int position, long mylng) {
  
			   	  // On Category selection
			   	  if( adapter.isSelectedListItemCategory(position)){
				   
			   		  Spinner categoryList = (Spinner) findViewById(R.id.category_list);
				   
			   		  int catIndex = adapter.getCategoryIndexFromFlatIndex(position);
				   
			   		  categoryList.setSelection(catIndex, true);
				   
				   }else{
					  return;  // On item selection
				   }
    	      }                 
    	});
       
       shoppingList.setAdapter(adapter.getShoppingAdapter());
       categoryList.setAdapter(adapter.getCategoryAdapter());
      
    }
    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_go_shop, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
    	Intent intent;
        switch (item.getItemId()) {
            case R.id.addCategory1:
            	intent = new Intent(this, AddCategoryActivity.class);
            	startActivityForResult(intent, ADD_CATEGORY_REQUEST_CODE);
                return true;
            case R.id.removeCategory:
            	intent = new Intent(this, RemoveCategoryActivity.class);
            	startActivityForResult(intent, REMOVE_CATEGORY_REQUEST_CODE);
            	return true;
            case R.id.clearList:
            	adapter.clearData();
            case R.id.categoryManager:
            	intent = new Intent(this, CategoryManagerActivity.class);
            	startActivity(intent);
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == ADD_CATEGORY_REQUEST_CODE) {
        	
        	String newCategoryName = data.getStringExtra(AddCategoryActivity.ADDED_CATEGORY_ID);
        	int color = data.getIntExtra(AddCategoryActivity.CATEGORY_COLOR_ID, Color.BLACK);
        	
        	adapter.addCategory(newCategoryName, color);
        	
        	Toast toast = Toast.makeText(this, "Category Added", Toast.LENGTH_SHORT);
        	toast.show();

        } 
    }
    
   public void addQuickItem(View view){
    	EditText editText = (EditText) findViewById(R.id.quick_add_text);
    	Spinner categoryList = (Spinner) findViewById(R.id.category_list);
    	
    	String itemName = editText.getText().toString();
    	int categoryPosition = categoryList.getSelectedItemPosition();
    	
    	//TODO It's technically fine if the category name is null, just add it to default
    	if(categoryPosition < 0) {
	    	Toast butter = Toast.makeText(this, "Could not add Item, no category found.", Toast.LENGTH_LONG);
	    	butter.show();
    	} else {
	    	
    		adapter.addItem(itemName, categoryPosition);
	    	
	    	editText.setText("");
	    	int duration = Toast.LENGTH_SHORT;
	
	    	Toast toast = Toast.makeText(this, "Item Added", duration);
	    	toast.show();
    	}
    }   
   
   public void removeQuickItem(View view) {
	   //TextView itemName = (TextView) findViewById(R.id.item_name);
	   ListView listView = (ListView) findViewById(R.id.shopping_list_view);
	   
	   int pos = listView.getPositionForView(view);
	  
	   adapter.removeItem(pos);
   }
   
   public void toggleCheckbox(View view){
	   
	   ListView listView = (ListView) findViewById(R.id.shopping_list_view);
	   int pos = listView.getPositionForView(view);
	   System.out.println("Checkbox "+pos+" toggled");
   }
   
   public void onResume(){
	   super.onResume();
	   
	   adapter.refreshAdapterData();
   }
    
}
