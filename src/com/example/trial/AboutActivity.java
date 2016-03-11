package com.example.trial;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class AboutActivity extends Activity{
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.about_options, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about_layout);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		if(item.getItemId()==R.id.item1)
		{
	        Intent intnt = new Intent(this, ProfileActivity.class);
			startActivity(intnt);
	        
		}
		else if(item.getItemId()==R.id.item2)
		{
			Intent intent = new Intent(this, BrowserActivity.class);
	        startActivity(intent);
	        
	        
		}
		else if(item.getItemId()==R.id.easy)
		{
			Intent intent = new Intent(this, EasyGo.class);
	        startActivity(intent);
	        
		}
		return super.onOptionsItemSelected(item);
		
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    if(event.getAction() == KeyEvent.ACTION_DOWN){
	        switch(keyCode)
	        {
	        case KeyEvent.KEYCODE_BACK:	        
	              super.onBackPressed();
	            return true;
	        }

	    }
	    return super.onKeyDown(keyCode, event);
	}

}
