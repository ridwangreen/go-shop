package com.example.goshop;

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
import android.widget.TextView;
import android.widget.Toast;

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
       
       // TODO this shit doesn't work.
       shoppingList.setOnItemClickListener(new OnItemClickListener() {
    	      public void onItemClick(AdapterView<?> myAdapter, View myView, int position, long mylng) {
    	    	  
				  ListView shoppingList = (ListView) findViewById(R.id.shopping_list_view);
			   
			   	  int itemIndex = shoppingList.getSelectedItemPosition();
			   	   
			   	  // On Category selection
			   	  if( adapter.isSelectedListItemCategory(itemIndex)){
				   
			   		  Spinner categoryList = (Spinner) findViewById(R.id.category_list);
				   
			   		  // I need to find the category by FLAT LIST index!!!!
			   		  int catIndex = adapter.getCategoryIndexFromFlatIndex(itemIndex);
				   
			   		  categoryList.setSelection(catIndex);
				   
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
        switch (item.getItemId()) {
            case R.id.addCategory1:
            	Intent intent = new Intent(this, AddCategoryActivity.class);
            	startActivityForResult(intent, ADD_CATEGORY_REQUEST_CODE);
                return true;
            case R.id.removeCategory:
            	Intent intent1 = new Intent(this, RemoveCategoryActivity.class);
            	startActivityForResult(intent1, REMOVE_CATEGORY_REQUEST_CODE);
            	return true;
            case R.id.clearList:
            	adapter.clearData();
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

        } else if (resultCode == RESULT_OK && requestCode == REMOVE_CATEGORY_REQUEST_CODE) {
        	String toRemove = data.getStringExtra(RemoveCategoryActivity.REMOVE_CATEGORY_ID);
        	adapter.removeCategory(toRemove);
        	
        	Toast toast = Toast.makeText(this, "Category Removed", Toast.LENGTH_SHORT);
        	toast.show();

        }
    }
    
   public void addQuickItem(View view){
    	EditText editText = (EditText) findViewById(R.id.quick_add_text);
    	Spinner categoryList = (Spinner) findViewById(R.id.category_list);
    	
    	String itemName = editText.getText().toString();
    	Object categoryName = categoryList.getSelectedItem();
    	
    	//TODO It's technically fine if the category name is null, just add it to default
    	if(categoryName == null) {
	    	Toast butter = Toast.makeText(this, "Could not add Item, no category found.", Toast.LENGTH_LONG);
	    	butter.show();
    	} else {
	    	
    		adapter.addItem(itemName, categoryName.toString());
	    	
	    	editText.setText("");
	    	int duration = Toast.LENGTH_SHORT;
	
	    	Toast toast = Toast.makeText(this, "Item Added", duration);
	    	toast.show();
    	}
    }   
   
   public void removeQuickItem(View view) {
	   TextView itemName = (TextView) findViewById(R.id.item_name);
	   ListView listView = (ListView) findViewById(R.id.shopping_list_view);
	   
	   int pos = listView.getPositionForView(view);
	  
	   System.out.println("Deleting item" + itemName.getText());
	   //System.out.println(findViewById(R.id.item_delete_button));
	   //System.out.println(view.getTag());
	   System.out.println("*******ITEM INDEX: " + pos);
	   adapter.removeItem(pos);
   }
    
}
