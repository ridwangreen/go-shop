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
import android.widget.Toast;

import com.example.data.Category;
import com.example.data.DataModelInterface;
import com.example.data.ItemManager;


public class GoShopActivity extends Activity {
	private static int ADD_CATEGORY_REQUEST_CODE = 12354;
	private ShoppingListAdapter shoppingListAdapter;
	private CategoryListAdapter categoryListAdapter;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
	   super.onCreate(savedInstanceState);
	   setContentView(R.layout.activity_go_shop);
	   
       ListView shoppingList = (ListView) findViewById(R.id.shopping_list_view);
       Spinner categoryList = (Spinner) findViewById(R.id.category_list);
       
       DataModelInterface data = new ItemManager();
       
       shoppingListAdapter = new ShoppingListAdapter(this, data);
       categoryListAdapter = new CategoryListAdapter(this, data);
       
       
       // TODO this shit doesn't work.
       shoppingList.setOnItemClickListener(new OnItemClickListener() {
    	      public void onItemClick(AdapterView<?> myAdapter, View myView, int position, long mylng) {
    	    	  
				  ListView shoppingList = (ListView) findViewById(R.id.shopping_list_view);
			   
			   	  int itemIndex = shoppingList.getSelectedItemPosition();
			   	   
			   	  // On Category selection
			   	  if( shoppingListAdapter.isSelectedListItemCategory(itemIndex)){
				   
			   		  Spinner categoryList = (Spinner) findViewById(R.id.category_list);
				   
			   		  // I need to find the category by FLAT LIST index!!!!
			   		  int catIndex = shoppingListAdapter.getCategoryIndexFromFlatIndex(itemIndex);
				   
			   		  categoryList.setSelection(catIndex);
				   
				   }else{
					  return;  // On item selection
				   }
    	      }                 
    	});
       
       shoppingList.setAdapter(shoppingListAdapter);
       categoryList.setAdapter(categoryListAdapter);

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
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println("Resulting Activities");
    	// If the request went well (OK) and the request was PICK_CONTACT_REQUEST
        if (resultCode == RESULT_OK && requestCode == ADD_CATEGORY_REQUEST_CODE) {
        	String newCategoryName = data.getStringExtra(AddCategoryActivity.ADDED_CATEGORY_ID);
        	shoppingListAdapter.addCategory(newCategoryName, Color.BLACK);
        	categoryListAdapter.add(new Category(newCategoryName, Color.BLACK));

        }
    }
    
   public void addQuickItem(View view){
    	EditText editText = (EditText) findViewById(R.id.quick_add_text);
    	Spinner categoryList = (Spinner) findViewById(R.id.category_list);
    	
    	String itemName = editText.getText().toString();
    	String categoryName = categoryList.getSelectedItem().toString();

    	shoppingListAdapter.addItem(itemName, categoryName);
    	
    	editText.setText("");
    	int duration = Toast.LENGTH_SHORT;

    	Toast toast = Toast.makeText(this, "Item Added", duration);
    	toast.show();
    }    
   
   public void deleteItem(View view){
	   
   }
    
}
