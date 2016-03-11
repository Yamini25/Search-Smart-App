package com.example.trial;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MenuAct extends Activity{
 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.first);
		
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
			Toast.makeText(this, "View profile page should be opened ", Toast.LENGTH_LONG).show();
		}
		else if(item.getItemId()==R.id.m3)
		{
			Intent intent = new Intent(this, AboutActivity.class);
	        startActivity(intent);
	        finish();
		}
		return super.onOptionsItemSelected(item);
	}
	
}
