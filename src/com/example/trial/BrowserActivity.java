package com.example.trial;


import android.app.TabActivity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class BrowserActivity extends TabActivity{
	SQLiteDatabase gdata;
	TabHost tabHost;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		tabHost = getTabHost();
		tabHost.setup();

		TabSpec spec1 = tabHost.newTabSpec("TAB 1");
       
		spec1.setContent(new Intent(this, GlobalActivity.class));
		spec1.setIndicator("Global Search");

		TabSpec spec2 = tabHost.newTabSpec("TAB 2");
		spec2.setIndicator("Local Search");
		
		spec2.setContent(new Intent(this, LocalActivity.class));

		tabHost.addTab(spec1);
		tabHost.addTab(spec2);
       tabHost.setBackgroundColor(Color.BLACK);
	   
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.options_menu, menu);
		return super.onCreateOptionsMenu(menu);
		
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		 if(item.getItemId()==R.id.m2)
		{
	        Intent intnt = new Intent(this, ProfileActivity.class);
			startActivity(intnt);
	     
		}
		else if(item.getItemId()==R.id.m3)
		{
			Intent intent = new Intent(this, AboutActivity.class);
	        startActivity(intent);
	        
	        
		}
		else if(item.getItemId()==R.id.easy)
		{
			Intent intent = new Intent(this, EasyGo.class);
	        startActivity(intent);
	        
	        
		}
		return super.onOptionsItemSelected(item);
	}

}
