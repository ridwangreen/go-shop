package com.example.goshop;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;

import com.example.data.ControllerInterface;
import com.example.data.ItemManager;

public class GoShopActivity extends Activity {

	private ControllerInterface data;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
	   super.onCreate(savedInstanceState);
	   setContentView(R.layout.shopping_list);
	    
	   data = new ItemManager();
	   
       ListView shoppingList = (ListView) findViewById(R.id.shopping_list_view);

       CategoryListAdapter shoppingListAdapter = new CategoryListAdapter(this, data, CategoryListAdapter.makeTestList());

       shoppingList.setAdapter(shoppingListAdapter);

    }
    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_go_shop, menu);
        return true;
    }
    
   /* public void addQuickItem(View view){
    	EditText editText = (EditText) findViewById(R.id.quick_add_text);
    	String itemName = editText.getText().toString();
    	Item newItem = new Item(itemName);
    	System.out.println(itemName);
    }*/
    
   
    
    
}
