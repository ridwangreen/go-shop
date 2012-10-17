package com.example.goshop;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

public class GoShopActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_go_shop);
        
       
    }
    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_go_shop, menu);
        return true;
    }
}
