package com.example.goshop;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

public class GoShopActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_go_shop);
        
        // Create a content resolver to get category information
        // Should be stored in XML or plain text
        // <Category name="Dairy" color="#8f8f8f"> 
        //	<Item name="foobar" />
        //	<Item name="fiddly" />
        // </Category>
        
    }
    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_go_shop, menu);
        return true;
    }
}
